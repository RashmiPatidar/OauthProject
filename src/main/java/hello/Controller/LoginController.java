/*******************************************************************************
 * Copyright (c) 2019.
 * This  code file/snippet/block, including any other configuration files,
 * is for the sole use of the Evive Health, LLC and may contain business
 * confidential and privileged information.
 * Any unauthorized review, use, disclosure or distribution is prohibited.
 ******************************************************************************/

package hello.Controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author evivehealth on 2019-03-27.
 */
@Controller
public class LoginController {
  @RequestMapping("/")
  public String home()
  {
   return "example.html" ;
  }

  @RequestMapping("login")
  public String login(){
    return "signin.html";
  }

  @RequestMapping("logout-success")
  public String logout(){
    return "dashboard.html";
  }

  @RequestMapping("user")
  @ResponseBody
  public Principal user(Principal principal){
    return principal;
  }
}
