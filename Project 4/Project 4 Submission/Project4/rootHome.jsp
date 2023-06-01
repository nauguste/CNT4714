<%
   String firstName = (String) session.getAttribute("SQLQuery");

   String message = (String) session.getAttribute("message");
   if (message == null) message = " ";
%>
<html lang="en">
<head>
<meta charset="utf-8">
<style type="text/css">
    <!--

    textarea 
    {        
        display: block;
        margin-left: auto;
        margin-right: auto;
    }

    body{background-color: black;}
    
    
    .button
    {
        display: flex;
        justify-content: center;
        border-radius: 6px;
        color: white;
        padding: 16px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        background-color: #2F2F2F;
        
    }

    .button:hover
    {
        background-color: #5F5F5F;
        color: white;
    }
    .flex-parent 
    {
        display: flex;
    }

    .jc-center 
    {
        justify-content: center;
    }
    

	-->
</style>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
    function eraseText()
    {
        textarea.value = ''
        
    }
</script>
    
<script type="text/javascript">
    function eraseData()
    {
        $("#data").remove();
    }
</script>

<p class=MsoNormal align=center style='text-align:center; font-family: Verdana; color:red;'>
<img width=225 height=225 id="_x0000_i1025" src="https://styles.redditmedia.com/t5_2etapp/styles/communityIcon_s4ezri13nda91.png">

<br>
    
<b><span style='font-size:24.0pt'>Welcome to Fall 2022 Project 4 Enterprise Data System</span></b>

<br>

<b><span style='font-size:16.0pt; color:orange;'>A Servlet/JSP-Based Multi-tiered Enterprise Application Using A Tomcat Container</span></b>

<br>
    
<hr size=2 width="100%" align=center>
</head>

<body>
    <p><p style="text-align: center; font-family: Verdana; color: white">
        You are now connected to the Project 4 Enterprise System Database as a <span
style='color:red;'> root-level </span> user. Please enter any valid SQL query or update command in the box below.
    </p>

<textarea id="SQLQuery" name="SQLQuery" rows="10" cols="200" style = "background-color: #292929; font-family: Verdana; color: white">Your Command Goes Here.</textarea>
    
<br>

<form action = "/Project4/Project4" method = "post" style="margin-top: 15px;" class="text-center">
<div class="flex-parent jc-center">
    <input type = "Button" Button Class = "Button" value = "Execute Command" name = "suppliersform"/> &nbsp; &nbsp; &nbsp;
    <input type = "Button" Button Class = "Button" value = "Clear Form" onclick = "javascript:eraseText();"/> &nbsp; &nbsp; &nbsp;
    <input type = "Button" Button Class = "Button" value = "Clear Results" onclick = "javascript:eraseData();"/> &nbsp; &nbsp; &nbsp;
</div>
</form>

<hr size=2 width="100%" align=center>
   
<p><p style="text-align: center; font-family: Verdana; color: white">
    All execution results will appear below.
</p>    

<hr size=2 width="100%" align=center>

<p><p style="text-align: center; font-family: Verdana; color: white">
    Database Results:
</p>
    
</body>
    
</html>