package io.cloud.auth.common.dtl;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-30 17:20
 **/
@Data
public class ImageCode {

    /**
     * 图片
     */
    private BufferedImage image;

    /**
     * 验证码
     */
    private  String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;


    /**
     * @param image
     * @param code
     * @param expireInt :该参数是过期时间秒数,如180
     */
    public ImageCode(BufferedImage image, String code, int expireInt) {
        this.image = image;
        this.code = code;
        //当前时间加上180秒
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
