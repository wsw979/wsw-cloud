package io.cloud.gateway.common.dtl;


import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-14 14:34
 **/
@Data
public class RouteDefinition {

    private String id;

    private List<PredicateDefinition> predicates;

    private List<FilterDefinition> filters;

    private URI uri;

    private int order = 0;

    public RouteDefinition() {
    }

    public RouteDefinition(String text) {
        int eqIdx = text.indexOf(61);
        if (eqIdx <= 0) {
            throw new ValidationException("Unable to parse RouteDefinition text '" + text + "', must be of the form name=value");
        } else {
            this.setId(text.substring(0, eqIdx));
            String[] args = StringUtils.tokenizeToStringArray(text.substring(eqIdx + 1), ",");
            this.setUri(URI.create(args[0]));

            for (int i = 1; i < args.length; ++i) {
                this.predicates.add(new PredicateDefinition(args[i]));
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            RouteDefinition routeDefinition = (RouteDefinition) o;
            return Objects.equals(this.id, routeDefinition.id) && Objects.equals(this.predicates, routeDefinition.predicates) && Objects.equals(this.order, routeDefinition.order) && Objects.equals(this.uri, routeDefinition.uri);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.predicates, this.uri});
    }

    @Override
    public String toString() {
        return "RouteDefinition{id='" + this.id + '\'' + ", predicates=" + this.predicates + ", filters=" + this.filters + ", uri=" + this.uri + ", order=" + this.order + '}';
    }
}
