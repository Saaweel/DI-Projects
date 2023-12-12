package com.saaweel;

import com.saaweel.controllers.ChatListCellController;
import io.github.palexdev.materialfx.controls.MFXContextMenu;
import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.api.APICallback;
import org.example.api.ChatAPIClient;
import org.example.api.model.Chat;
import org.example.api.model.Error;

import javax.swing.border.Border;

public class ChatListCell extends ListCell<Chat> {
    private Chat chatOpened;
    private BorderPane chatPane;
    private VBox chatMessages;
    private ObservableList<Chat> chatList;
    public ChatListCell(BorderPane chatPane, VBox chatMessages, ObservableList<Chat> chatList, Chat chatOpened) {
        this.chatPane = chatPane;
        this.chatMessages = chatMessages;
        this.chatList = chatList;
        this.chatOpened = chatOpened;
    }

    @Override
    protected void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);

        if (chat == null || empty) {
            setText(null);
            setGraphic(null);
            setBackground(null);
        } else {
            setText(null);
            setBackground(null);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chatlistcell.fxml"));
                Parent root = loader.load();
                ChatListCellController controller = loader.getController();
                controller.setData(chat);
                setGraphic(root);

                MFXContextMenu menu = new MFXContextMenu(root);

                MFXContextMenuItem clearItem = new MFXContextMenuItem("Limpiar chat");

                ChatAPIClient chatApi = new ChatAPIClient();

                clearItem.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (event.isPrimaryButtonDown()) {
                        chatApi.cleanChat(chat.getId(), new APICallback() {
                            @Override
                            public void onSuccess(Object response) {
                                if (chatOpened.getId() == chat.getId())
                                    chatMessages.getChildren().clear();
                            }

                            @Override
                            public void onError(Object response) {
                                if (response instanceof Error) {
                                    Error error = (Error) response;
                                    App.showNotification("Error al limpiar el chat", error.getError());
                                }
                            }
                        });
                    }
                });

                MFXContextMenuItem deleteItem = new MFXContextMenuItem("Eliminar chat");

                deleteItem.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (event.isPrimaryButtonDown()) {
                        chatApi.deleteChat(chat.getId(), new APICallback() {
                            @Override
                            public void onSuccess(Object response) {
                                if (chatOpened.getId() == chat.getId())
                                    chatPane.setVisible(false);

                                chatList.remove(chat);
                            }

                            @Override
                            public void onError(Object response) {
                                if (response instanceof Error) {
                                    Error error = (Error) response;
                                    App.showNotification("Error al borrar el chat", error.getError());
                                }
                            }
                        });
                    }
                });

                menu.addItem(clearItem);
                menu.addItem(deleteItem);

                menu.install();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
