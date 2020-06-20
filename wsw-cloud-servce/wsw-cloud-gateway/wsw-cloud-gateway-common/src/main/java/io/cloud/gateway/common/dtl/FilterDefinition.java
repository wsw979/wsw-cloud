package io.cloud.gateway.common.dtl;

import io.cloud.gateway.common.util.NameUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-14 14:35
 **/
@Data
@EqualsAndHashCode
public class FilterDefinition {

    private String name;

    private Map<String, String> args = new LinkedHashMap();

    public FilterDefinition() {
    }

    public FilterDefinition(String text) {
        int eqIdx = text.indexOf(61);
        if (eqIdx <= 0) {
            this.setName(text);
        } else {
            this.setName(text.substring(0, eqIdx));
            String[] args = StringUtils.tokenizeToStringArray(text.substring(eqIdx + 1), ",");

            for(int i = 0; i < args.length; ++i) {
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
            FilterDefinition that = (FilterDefinition)o;
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
        StringBuilder sb = new StringBuilder("FilterDefinition{");
        sb.append("name='").append(this.name).append('\'');
        sb.append(", args=").append(this.args);
        sb.append('}');
        return sb.toString();
    }
}
