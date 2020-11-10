package ch.heigvd.broccoli.badge;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class BadgeModelAssembler implements RepresentationModelAssembler<Badge, EntityModel<Badge>> {

    @Override
    public EntityModel<Badge> toModel(Badge badge) {
        return EntityModel.of(badge,
                linkTo(methodOn(BadgeController.class).one(badge.getId())).withSelfRel(),
                linkTo(methodOn(BadgeController.class).all()).withRel("badges"));
    }

    @Override
    public CollectionModel<EntityModel<Badge>> toCollectionModel(Iterable<? extends Badge> badges) {
        List<EntityModel<Badge>> list = new ArrayList<>();
        badges.forEach(badge -> list.add(toModel(badge)));
        return CollectionModel.of(list, linkTo(methodOn(BadgeController.class).all()).withSelfRel());
    }

}
