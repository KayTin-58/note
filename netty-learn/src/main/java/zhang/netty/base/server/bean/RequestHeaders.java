package zhang.netty.base.server.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * description
 *
 * @author zb 2019/07/24 14:33
 */

@Setter
@Getter
@Builder
public class RequestHeaders {
    private String menthodName;
    private String url;
    private String version;

    @Override
    public String toString() {
        return "RequestHeaders{" +
                "menthodName='" + menthodName + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
