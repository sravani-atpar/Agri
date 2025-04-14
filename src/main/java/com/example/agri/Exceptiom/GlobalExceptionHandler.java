//package com.example.agri.Exceptiom;
//
//import org.springframework.beans.factory.parsing.Problem;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ProblemDetail handleException(Exception e) {
//        ProblemDetail errorDetail = null;
////        Too send this  stack trace to an observalibility tool
//        e.printStackTrace();
//        if (e instanceof BadCredentialsException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
//            errorDetail.setProperty("description", "The Username or password is incorrect.");
//            return errorDetail;
//
//        }
//    }
//
//}
