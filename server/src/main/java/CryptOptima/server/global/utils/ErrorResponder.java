package CryptOptima.server.global.utils;

import CryptOptima.server.global.exception.ErrorResponse;
import com.google.gson.Gson;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// controller 범위 밖 filter chain에서 발생하는 exception에 대해 적용
public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, ErrorResponse.Default errorResponse) throws IOException {
        Gson gson = new Gson();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatus());

        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.Default.class));
    }

}
