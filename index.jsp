<%@ page import="java.util.List" %>
<%@ page import="com.example.model.SortStep" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!    // helper – Java int[] ➜ JS comma list
    public String intsToJs(int[] arr){
        if(arr==null) return "";
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<arr.length;i++){
            sb.append(arr[i]);
            if(i<arr.length-1) sb.append(",");
        }
        return sb.toString();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sorting Visualizer (Bubble Sort)</title>

<!-- 🎨  PURE CSS  -->
<style>
:root{
    --bar-color:#88c4d4;
    --bar-width:40px;
    --swap-speed:350ms;
    font-family:Arial,Helvetica,sans-serif;
}
body{
    margin:0;
    height:100vh;
    display:flex;
    align-items:center;
    justify-content:center;
    background:#fafafa;
}
.wrapper{
    text-align:center;
    padding:30px 40px 50px;
    border:1px solid #ccc;
    border-radius:12px;
    background:#fff;
    box-shadow:0 4px 12px rgba(0,0,0,.08);
}
h2{margin-top:0;}
input[type=text]{
    padding:6px 10px;width:260px;font-size:15px;
    border-radius:6px;border:1px solid #666;outline:none;
}
button{
    padding:7px 14px;font-size:15px;border:0;
    border-radius:6px;cursor:pointer;
    background:#4CAF50;color:#fff;margin-left:6px;
}
#bars{
    margin-top:40px;
    display:flex;align-items:flex-end;gap:8px;height:260px;
}
.bar{
    width:var(--bar-width);
    background:var(--bar-color);
    border-radius:6px 6px 0 0;
    transition:transform var(--swap-speed),background .2s ease;
    position:relative;
}
.bar.swapping{background:#f39c12;}
.bar:hover::after{
    content:attr(data-val);
    position:absolute;top:-24px;left:50%;
    transform:translateX(-50%);
    padding:2px 6px;font-size:13px;color:#fff;
    background:#333;border-radius:4px;
}
</style>
</head>

<body>
<div class="wrapper">
    <h2>Sorting Visualizer <small>(Bubble Sort)</small></h2>

    <!-- ==== Input Form ==== -->
    <form method="post" action="sort" style="margin-bottom:20px;">
        <label>Enter numbers (comma separated):</label>
        <input type="text" name="numbers" placeholder="e.g. 5,2,9,1" required />
        <button type="submit">Sort</button>
    </form>

    <!-- ==== Bars Area ==== -->
    <div id="bars"></div>

<%-- ======  JSP embeds steps coming from servlet  ====== --%>
<%
    List<SortStep> steps = (List<SortStep>)request.getAttribute("steps");
    if (steps != null && !steps.isEmpty()) {
%>
<script>
    /* ---------- DATA injected from server ---------- */
    const steps = [
        <% for(int i=0;i<steps.size();i++){
               com.example.model.SortStep s = steps.get(i);
        %>
        { array:[<%= intsToJs(s.getArray()) %>],
          highlights:[<%= intsToJs(s.getHighlights()) %>] }<%= (i<steps.size()-1?",":"") %>
        <% } %>
    ];
</script>
<% } else { %>
<script>const steps = [];</script>
<% } %>

<!-- ==========  Pure front‑end logic ========== -->
<script>
const barsDiv = document.getElementById("bars");
let current = 0;

function drawStep(step){
   const {array, highlights} = step;
   const max = Math.max(...array);
   barsDiv.innerHTML = "";

   array.forEach((val,idx)=>{
        const bar = document.createElement("div");
        bar.className = "bar";
        bar.style.height = (val/max*100)+"%";
        bar.dataset.val = val;
        if(highlights.includes(idx)) bar.classList.add("swapping");
        barsDiv.appendChild(bar);
   });
}

function play(){
    if(current >= steps.length) return;
    drawStep(steps[current++]);
    setTimeout(play, parseFloat(getComputedStyle(document.documentElement)
                      .getPropertyValue('--swap-speed')));
}

/* Auto‑start animation if data exists */
window.onload = ()=>{
    if(steps.length){
        drawStep(steps[0]);
        setTimeout(play,500);
    }
};
</script>
</div>
</body>
</html>
