package ru.agibase.web.history.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Setter;

import java.util.function.Consumer;

public class Pagination extends VerticalLayout {

    @Setter
    private Consumer<Integer> newPageConsumer;

    private int page;

    private TextField pageField;
    private Button nextPageButton;
    private Button previousPageButton;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        pageField = new TextField("Page");
        pageField.setReadOnly(true);

        nextPageButton = new Button("Next >");
        nextPageButton.setDisableOnClick(true);
        nextPageButton.addClickListener(event -> {
            newPageConsumer.accept(page + 1);
        });

        previousPageButton = new Button("< Prev");
        previousPageButton.setDisableOnClick(true);
        previousPageButton.addClickListener(event -> {
            newPageConsumer.accept(page - 1);
        });

        add(
                pageField,
                new HorizontalLayout(previousPageButton, nextPageButton)
        );
        setAlignItems(Alignment.CENTER);
        setSpacing(false);
    }

    public void refresh(int page, int totalPages) {
        this.page = page;
        if (totalPages == 0) {
            pageField.setValue("History not found");
        } else {
            pageField.setValue("%d / %d".formatted(page + 1, totalPages));
        }
        nextPageButton.setEnabled(page < totalPages - 1);
        previousPageButton.setEnabled(page > 0);
    }
}
