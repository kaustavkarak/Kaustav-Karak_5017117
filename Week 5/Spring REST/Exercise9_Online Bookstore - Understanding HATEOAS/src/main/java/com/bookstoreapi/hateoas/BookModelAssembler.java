package com.example.bookstore.hateoas;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.model.Book;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

    @Override
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
    }

    @Override
    public CollectionModel<EntityModel<Book>> toCollectionModel(Iterable<? extends Book> entities) {
        CollectionModel<EntityModel<Book>> bookModels = RepresentationModelAssembler.super.toCollectionModel(entities);
        bookModels.add(linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
        return bookModels;
    }
}
