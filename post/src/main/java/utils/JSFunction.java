package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {

    // JSP 페이지에서 alert 메시지 후, 특정 URL로 리다이렉트하는 함수
    public static void alertLocation(String msg, String url, JspWriter out) {
        try {
            // 자바스크립트 코드 생성
            String script = "<script>"  
                          + "    alert('" + msg + "');"  // 메시지 알림
                          + "    location.href='" + url + "';"  // 주어진 URL로 리다이렉트
                          + "</script>";
            out.println(script);  // 자바스크립트 코드를 JSP 페이지에 출력
        }
        catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }

    // JSP 페이지에서 alert 메시지 후, 이전 페이지로 돌아가는 함수
    public static void alertBack(String msg, JspWriter out) {
        try {
            // 자바스크립트 코드 생성
            String script = "<script>"
                          + "    alert('" + msg + "');"  // 메시지 알림
                          + "    history.back();"  // 이전 페이지로 돌아감
                          + "</script>";
            out.println(script);  // 자바스크립트 코드를 JSP 페이지에 출력
        }
        catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }

    // 서블릿에서 alert 메시지 후, 특정 URL로 리다이렉트하는 함수
    public static void alertLocation(HttpServletResponse resp, String msg, String url) {
        try {
            resp.setContentType("text/html;charset=UTF-8");  // 응답 콘텐츠 타입 설정
            PrintWriter writer = resp.getWriter();  // 응답 출력 스트림 가져오기
            // 자바스크립트 코드 생성
            String script = "<script>"
                          + "    alert('" + msg + "');"  // 메시지 알림
                          + "    location.href='" + url + "';"  // 주어진 URL로 리다이렉트
                          + "</script>";
            writer.print(script);  // 자바스크립트 코드를 HTTP 응답에 출력
        }
        catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }

    // 서블릿에서 alert 메시지 후, 이전 페이지로 돌아가는 함수
    public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/html;charset=UTF-8");  // 응답 콘텐츠 타입 설정
            PrintWriter writer = resp.getWriter();  // 응답 출력 스트림 가져오기
            // 자바스크립트 코드 생성
            String script = "<script>"
                          + "    alert('" + msg + "');"  // 메시지 알림
                          + "    history.back();"  // 이전 페이지로 돌아감
                          + "</script>";
            writer.print(script);  // 자바스크립트 코드를 HTTP 응답에 출력
        }
        catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }
}
