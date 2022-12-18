<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<head>
	<link rel="stylesheet" href="/p3/errorPages/error500.css">
</head>

<input id="a" type="checkbox">
<label for="a" class="bug">
  
  <div class="fr">
    <div class="hd"></div>
    <div class="leg leg-r"></div>
    <div class="leg leg-l"></div>
  </div>
  <div class="re">
    <div class="leg leg-r-r"></div>
    <div class="leg leg-r-l"></div>
    <div class="leg leg-r-r aa"></div>
    <div class="leg leg-r-l aa"></div>
  </div>
</label>

<label for="a" class="pop-up">
  <p>
    You have caught the bug, click this pop-up to try again.
  </p>
</label>

<div class="error">
  Error: 500
  <br>
  Try to catch the bug while we are trying to solve our problems.
</div>