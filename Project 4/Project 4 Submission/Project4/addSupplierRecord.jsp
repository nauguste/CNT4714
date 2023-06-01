<html lang="en">
<head>
<meta charset="utf-8">
<style type="text/css">
    <!--

    body{background-color: black;}
    
    table, th, td
    { 
        border: 1px solid white;
        text-align: center;
        color: white;
        background-color: black;
    }
    
    table
    {
        width: 100%;
    }
    
    th
    {
        color: #49FD44;
        width: auto;
    }
    
    td
    {
        color: #49FD44;
        width: auto;
    }
    
    fieldset
    {
        
        display: block;
        margin: 1.0em auto;
        text-align: center;
        border: 1px solid white; 
        color: white; 
        padding: 30px; 
        width: 60%;
        
        
    }
    
    legend
    {
        text-align: center;
        font-family: Verdana;
        color: white;
    }
    
    input[type=text]
    {
        text-align: center;
        background-color: #3F3F3F;
        color: white;
        width: auto;
    }
    
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
        vertical-align:middle; 
    }
    
	-->
</style>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="application/javascript">
    function eraseData()
    {
        $("#data").remove();
    }
</script>

<p class=MsoNormal align=center style='text-align:center; font-family: Verdana; color:#49FD44;'>
<img width=225 height=225 id="_x0000_i1025" src="https://styles.redditmedia.com/t5_2etapp/styles/communityIcon_s4ezri13nda91.png">

<br>
    
<b><span style='font-size:24.0pt'>Welcome to Fall 2022 Project 4 Enterprise Data System</span></b>

<br>

<b><span style='font-size:16.0pt; color:orange;'>Data Entry Application</span></b>

<br>
    
<hr size=2 width="100%" align=center>
</head>

<body>
<p><p style="text-align: center; font-family: Verdana; color: white">
        You are now connected to the Project 4 Enterprise System Database as a <span style='color:#49FD44;'> client-level </span> user. 
<br> 
        Please enter any valid SQL query or update command in the box below.
</p>
    
<hr size=2 width="100%" align=center>
    

<form action="AddSupplierRecord" method ="post">
    
    <fieldset>
    <legend> Supplier Record Insert</legend>
    <table>
      <tr>
        <th>snum</th>
        <th>sname</th>
        <th>status</th>
        <th>city</th>
      </tr>
      <tr>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
      </tr>
    </table>
    <br>
        
    <input type = "Button" Button Class = "Button" value = "Enter Supplier Record Into Database" name = "suppliersform"/> &nbsp; &nbsp; &nbsp;  
    <input type = "Button" Button Class = "Button" value = "Clear Results" onclick = "javascript:eraseData();"/> &nbsp; &nbsp; &nbsp;
        
    </fieldset>
    
</form>   

    
<br>
    
<form action="AddPartRecord" method ="post"> 
    
    <fieldset>
    <legend> Parts Record Insert</legend>
    <table>
      <tr>
        <th>pnum</th>
        <th>pname</th>
        <th>color</th>
        <th>weight</th>
        <th>city</th>
      </tr>
      <tr>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
      </tr>
    </table>
    <br>
        
    <input type = "Button" Button Class = "Button" value = "Enter Part Record Into Database" name = "partsform"/> &nbsp; &nbsp; &nbsp;  
    <input type = "Button" Button Class = "Button" value = "Clear Results" onclick = "javascript:eraseData();"/> &nbsp; &nbsp; &nbsp;
        
    </fieldset>
    
</form>
    
<br>
    
<form action="AddJobRecord" method ="post">    
    
    <fieldset>
    <legend> Jobs Record Insert</legend>
    <table>
      <tr>
        <th>jnum</th>
        <th>jname</th>
        <th>numworkers</th>
        <th>city</th>
      </tr>
      <tr>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
      </tr>
    </table>
    <br>
        
    <input type = "Button" Button Class = "Button" value = "Enter Part Record Into Database" name = "partsform"/> &nbsp; &nbsp; &nbsp;  
    <input type = "Button" Button Class = "Button" value = "Clear Results" onclick = "javascript:eraseData();"/> &nbsp; &nbsp; &nbsp;
        
    </fieldset>
    
</form>
    
<br>
    
<form action="AddShipmentRecord" method ="post">    
    
    <fieldset>
    <legend> Shipments Record Insert</legend>
    <table>
      <tr>
        <th>snum</th>
        <th>pnum</th>
        <th>jnum</th>
        <th>quantity</th>
      </tr>
      <tr>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
        <td><input type="text" id="fname" name="fname"></td>
      </tr>
    </table>
    <br>
        
    <input type = "Button" Button Class = "Button" value = "Enter Part Record Into Database" name = "partsform"/> &nbsp; &nbsp; &nbsp;  
    <input type = "Button" Button Class = "Button" value = "Clear Results" onclick = "javascript:eraseData();"/> &nbsp; &nbsp; &nbsp;
  
    </fieldset> 
    
</form>
<br>