package cl.blueprintsit.framework.auth.filter;

import cl.blueprintsit.framework.Constants;
import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.blueprintsit.framework.auth.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
public class AuthenticationFilter implements Filter{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (  req.getRequestURI().contains(Constants.LOGIN_PAGE) || req.getRequestURI().contains(Constants.ERRORS_FOLDER) || hasPermission(req)) {
            chain.doFilter(request, response);
        } else if (!isLoggedIn(req)) { //perdio la sesion

            String queryString = "";
            if(req.getQueryString()!=null)
                queryString="?" + req.getQueryString().replace("&", "$");
            String redirectURL = req.getContextPath() + "/" + Constants.VIEWS_FOLDER + "/" + Constants.LOGIN_PAGE + "?viewExpired=true&originalURI=" + req.getRequestURI() + queryString;
            res.sendRedirect(redirectURL);
            LOGGER.debug("usuario perdio sesion. redirigiendo a {}",redirectURL);


        } else if(!hasPermission(req)) {//no tiene permiso para acceder a la pagina solicitada
            String redirectURL = req.getContextPath() + "/" + Constants.VIEWS_FOLDER + "/" + Constants.ERRORS_FOLDER + "/" + Constants.AUTH_ERROR_PAGE;
            res.sendRedirect(redirectURL);
            LOGGER.debug("usuario sin sesion. redirigiendo a {}",redirectURL);

        } else {//otros casos que nunca deberian darse

            LOGGER.debug("Caso que no deberia pasar");
            res.sendRedirect(req.getContextPath() + "/" + Constants.VIEWS_FOLDER+ "/"  + Constants.LOGIN_PAGE );
        }

    }

    private boolean isLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute(AuthenticationBean.AUTH_KEY) != null;
    }

    private boolean hasPermission(HttpServletRequest req) {
        if (!isLoggedIn(req))
            return false;

        AuthenticationBean auth = (AuthenticationBean) req.getSession().getAttribute("authenticationBean");
        User u = auth.getLoggedUser();

        return u != null ;

    }



    public void destroy() {
        //nothing to do
    }


    public void init(FilterConfig arg0) throws ServletException {
        //nothing to do
    }


}


