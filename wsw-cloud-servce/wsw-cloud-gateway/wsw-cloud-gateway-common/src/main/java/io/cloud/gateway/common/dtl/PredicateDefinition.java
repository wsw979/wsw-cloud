package io.cloud.gateway.common.dtl;

import io.cloud.gateway.common.util.NameUtil;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-14 14:34
 **/
public class PredicateDefinition {

    private String name;

    private Map<String, String> args = new LinkedHashMap();

    public PredicateDefinition() {
    }

    public PredicateDefinition(String text) {
        int eqIdx = text.indexOf(61);
        if (eqIdx <= 0) {
            throw new ValidationException("Unable to parse PredicateDefinition text '" + text + "', must be of the form name=value");
        } else {
            this.setName(text.substring(0, eqIdx));
            String[] args = StringUtils.tokenizeToStringArray(text.substring(eqIdx + 1), ",");

            for (int i = 0; i < args.length; ++i) {
                this.args.put(NameUtil.generateName(i), args[i]);
            }

        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return this.args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    public void addArg(String key, String value) {
        this.args.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            PredicateDefinition that = (PredicateDefinition) o;
            return Objects.equals(this.name, that.name) && Objects.equals(this.args, that.args);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.args});
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PredicateDefinition{");
        sb.append("name='").append(this.name).append('\'');
        sb.append(", args=").append(this.args);
        sb.append('}');
        return sb.toString();
    }
}
