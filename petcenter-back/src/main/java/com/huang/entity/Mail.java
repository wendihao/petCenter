package com.huang.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class Mail {
 private String from;
 private String to;
 private String cc;
 private String subject;
 private String content;

 public String getFrom() {
  return from;
 }

 public void setFrom(String from) {
  this.from = from;
 }

 public String getTo() {
  return to;
 }

 public void setTo(String to) {
  this.to = to;
 }

 public String getCc() {
  return cc;
 }

 public void setCc(String cc) {
  this.cc = cc;
 }

 public String getSubject() {
  return subject;
 }

 public void setSubject(String subject) {
  this.subject = subject;
 }

 public String getContent() {
  return content;
 }

 public void setContent(String content) {
  this.content = content;
 }
}
