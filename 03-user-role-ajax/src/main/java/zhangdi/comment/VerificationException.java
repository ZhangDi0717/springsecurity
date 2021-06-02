package zhangdi.comment;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 验证码异常处理
 */
public class VerificationException extends AuthenticationException {

    public VerificationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public VerificationException(String msg) {
        super(msg);
    }

    public VerificationException() {
        super("验证码错误，请重新输入");
    }
}
