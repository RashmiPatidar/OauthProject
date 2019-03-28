/*******************************************************************************
 * Copyright (c) 2019.
 * This  code file/snippet/block, including any other configuration files,
 * is for the sole use of the Evive Health, LLC and may contain business
 * confidential and privileged information.
 * Any unauthorized review, use, disclosure or distribution is prohibited.
 ******************************************************************************/

package hello.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * @author evivehealth on 2019-03-04.
 */
@Service
public class SuccessHandler implements AuthenticationSuccessHandler {

  private static final RedirectStrategy REDIRECT_STRATEGY = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      Authentication authentication)
      throws IOException, ServletException {

    REDIRECT_STRATEGY.sendRedirect(httpServletRequest,
                                   httpServletResponse,
                                   "/dashboard");

  }
}
