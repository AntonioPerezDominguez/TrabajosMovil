package com.lixoten.fido.feature_notes.presentation.edit_note

import androidx.annotation.StringRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lixoten.fido.feature_notes.presentation._components_shared.MyBottomBar
import com.lixoten.fido.R
import com.lixoten.fido.feature_notes.model.Note
import com.lixoten.fido.feature_notes.presentation._components_shared.MyTextField
import com.lixoten.fido.feature_notes.presentation._components_shared.MyTopBar
import com.lixoten.fido.navigation.NavigationDestination
import com.lixoten.fido.ui.theme.Violet
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object EditNoteScreenDestination : NavigationDestination {
    override val route = "edit_note"

    @StringRes
    override val titleRes = R.string.edit_add_note_screen_name

    const val routeArg = "id="
    val routeWithArgs = "$route{$routeArg}"

}

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddEditNoteViewmodel = viewModel(factory = AddEditNoteViewmodel.Factory),
) {
    // addEditNoteViewModel.setCode(code)
    val uiState by viewModel.uiState.collectAsState()

    var openDialog by rememberSaveable { mutableStateOf(false) }

    val noteColor = -1
    val noteBackgroundAnimatable = remember {
        Animatable(
            //Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
            Color(uiState.note.color)
        )
    }
    val scope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewmodel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Scaffold(
        topBar = {
            MyTopBar(
                screenTitle = stringResource(id = EditNoteScreenDestination.titleRes),
                canNavigateUp = true,
                navigateUp = { navController.navigateUp() },
                onNavigationIconClick = { }
            )
        },
        floatingActionButton =
        if (uiState.dataHasChanged && !uiState.titleError) {
            {
                FloatingActionButton(
                    modifier = Modifier,
                    onClick = {
                        viewModel.onEvent(
                            AddEditNoteEvents.UpdateDbNotes(
                                Note(
                                    0,
                                    "",
                                    "",
                                    0,
                                    false,
                                    false
                                )
                            )
                        )
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = stringResource(R.string.save_note_img_desc)
                    )
                }
            }
        } else {
            if (!uiState.dataHasChanged && !uiState.titleError) {
                {
                    // Initial Load
                    FloatingActionButton(
                        modifier = Modifier,
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(imageVector = Icons.Default.Cancel, contentDescription = "Add Task")
                    }
                }

            } else {
                {
                }
            }
        },
        bottomBar = {
            MyBottomBar(
                isPinned = uiState.note.isPinned,
                isChecked = uiState.note.isChecked,
                hasDelete = uiState.note.id > 0,
                onDeleteClick = {
                    openDialog = true
                },
                onPinRecord = {
                    viewModel.onEvent(AddEditNoteEvents.UpdateStatePinned)
                },
                onCheckedChange = {
                    viewModel.onEvent(AddEditNoteEvents.UpdateStateCheck)
                }

            )
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        }

    ) {
        val focusManager = LocalFocusManager.current

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(
                    onClick = {
                        viewModel.onEvent(AddEditNoteEvents.UpdateStatePinned)
                    }
                ) {
                    Icon(
                        painter = if (uiState.note.isPinned) painterResource(id = R.drawable.ic_pin_filled)
                        else painterResource(id = R.drawable.ic_pin),
                        contentDescription = "pin",
                        tint = Violet,
                        modifier = Modifier.size(24.dp),
                    )
                }
                IconButton(
                    onClick = {
                        if (uiState.note.id > 0) {
                            openDialog = true
                        } else {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.LightGray
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Status :")
                    Checkbox(
                        checked = uiState.note.isChecked,
                        onCheckedChange = {
                            viewModel.onEvent(AddEditNoteEvents.UpdateStateCheck)
                        }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Surface(
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(color)
                                .clip(CutCornerShape(12.dp))
                                .shadow(15.dp, CutCornerShape(23.dp))
                                .border(
                                    width = 4.dp,
                                    color = if (uiState.note.color == colorInt) {
                                        Color.Black
                                    } else Color.Transparent,
                                    shape = CutCornerShape(20.dp)
                                )
                                .clickable {
                                    scope.launch {
                                        noteBackgroundAnimatable.animateTo(
                                            targetValue = Color(colorInt),
                                            animationSpec = tween(
                                                durationMillis = 500
                                            )
                                        )
                                    }
                                    viewModel.onEvent(AddEditNoteEvents.UpdateStateColor(colorInt))
                                }
                        )
                    }
                }
            }
            MyTextField(
                value = uiState.note.title,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvents.UpdateStateTitle(it))
                },
                onDone = { focusManager.moveFocus(FocusDirection.Down) },
                labelResId = if (uiState.titleError) R.string.add_input_label_error else R.string.add_input_label,
                placeHolderResId = R.string.add_input_placeholder,
                error = uiState.titleError,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(uiState.note.color))
            )
            Spacer(modifier = Modifier.height(8.dp))
            MyTextField(
                value = uiState.note.content,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvents.UpdateStateContent(it))
                },
                labelResId = R.string.add_content_label,
                placeHolderResId = R.string.add_content_placeholder,
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Color(uiState.note.color))
            )
        }
    }
    if (openDialog) {
        AlertDialog(
            shape = RoundedCornerShape(25.dp),
            onDismissRequest = { openDialog = false },
            title = { Text(stringResource(R.string.remove_note_button)) },
            text = {
                Text(
                    stringResource(
                        R.string.remove_note_confirmation_message,
                        uiState.note.title
                    )
                )
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        viewModel.onEvent(AddEditNoteEvents.RemoveDbNote(uiState.note))
                        navController.popBackStack()
                    },
                ) {
                    Text(stringResource(R.string.remove_note_button), color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        openDialog = false
                    }) {
                    Text(stringResource(R.string.cancel_button), color = Color.White)
                }
            }
        )
    }
}