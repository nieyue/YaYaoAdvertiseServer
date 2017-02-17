;(function(window){
document.querySelector('body').style.margin=0;
document.querySelector('body').style.padding=0;

var advertiseSpace=(function(){
/**
**对象初始化
*/

    function advertiseSpace(UI,advertiseSpaceConfig){
       //广告UI ，默认移动端配置
       this.UI={
        width:'95%',//宽度
        height:'100%',//高度
        backgroundColor:'#fff',//背景颜色
        position:"relative",//默认相对定位
        left:'0',//默认0
        top:'0',//默认0
        bottom:'auto',//默认取消，与top切换用
        margin:'0',//默认为0
        padding:'0',//默认为0
        textLine:2,//文字行数
        zindex:'1001'//深度
       };
     
      //多个广告位对象配置
    	this.advertiseSpaceConfig=[
      {
       	advertise_space_id:window.advertise_space_id||'',//广告位id
       	name:'',//名称
        platform:'移动端',//平台
        type:'悬浮',//类型
        location:'顶部',
        unit_price:'',
        now_unit_delivery_number:'',
        now_unit_money:'',
        status:'',//状态
        close:true,//是否开启关闭按钮，默认开启
        update_time:timeStampToDate(new Date()),//更新时间
        admin_id:'',//渠道主id
        advertiseList:[ //多个广告对象配置
        { advertise_id:'',//广告id
         name:'',//名称
         type:'',//类型
         subtype :'',//子类型
         text:"新浪娱乐讯 今日（1月17日），刘恺威、王鸥领衔主演的《莽荒纪》曝光了IMAX版人物海报。刘恺威、王鸥、陈亦飞等人化身莽荒英雄，手握神兵利器，破幕而出，利用出屏的动作设计以及横向的观看形式，摄人心魄可玩性十足。",
         img:['http://www.yayao8.com/resources/img/logo.jpg','http://www.yayao8.com/resources/img/logo.jpg','http://www.yayao8.com/resources/img/logo.jpg'],//图片路径
         link:'',//链接地址
         unit_price:'',
         unit_delivery_number:'',
         now_unit_delivery_number:'',
         unit_money:'',
         now_unit_money:'',
         status:'',//状态
         start_delivery_date:'',//投放开始时间
         end_delivery_date:'',//投放结束时间
         update_date:timeStampToDate(new Date()),//更新时间
         admin_id:''//广告主id
       }]
       }
       ];
      
		//$.extend(this.UI,UI);
    if(UI){
		this.UI=extendObj(this.UI,UI);
    }
     if(advertiseSpaceConfig){
    this.advertiseSpaceConfig=transform(extendObj(this.advertiseSpaceConfig,advertiseSpaceConfig));
    }
    }
      
    /**
    **对象方法定义
    */
    advertiseSpace.prototype={
/**
**配置整体广告
*/
        getAdvertiseSpaceConfig:function(){
          var _this=this;
    //_this.advertiseSpaceConfig.location='顶部';
   // _this.advertiseSpaceConfig.location='底部';
    //_this.advertiseSpaceConfig.type="悬浮";
  // _this.advertiseSpaceConfig.type="内嵌";
   
     return _this.advertiseSpaceConfig;
        },
/**
**获取广告层结构
*thisAdvertiseSpaceConfig getAdvertiseSpaceConfig()的返回值
*location  广告位置
*/
 getAdvertiseSpaceDiv:function(){
    var thisAdvertiseSpaceConfig=this.getAdvertiseSpaceConfig();
    var thisAdvertiseSpaceConfigLength=thisAdvertiseSpaceConfig.length;
           var  oldWidth=this.UI.width;
    for(var i=0;i<thisAdvertiseSpaceConfigLength;i++){      
      //移动端
      if(thisAdvertiseSpaceConfig[i].platform=="移动端"){

           //位置
           var location;
           this.UI.left=parseInt((100-parseInt(oldWidth.slice(0,-1)))/2)+'%';
          if(thisAdvertiseSpaceConfig[i].type=='内嵌'){
            this.UI.position='relative';
            this.UI.zindex='9999'; 
            this.UI.width=oldWidth;  
          }

          if(thisAdvertiseSpaceConfig[i].type=='悬浮'){
               this.UI.position='fixed';
               this.UI.zindex='10002';
               this.UI.margin='0';
               this.UI.width="100%";
               this.UI.left='0';
                 
          }
           if(thisAdvertiseSpaceConfig[i].location=='顶部'){
              location='insertBefore';
              this.UI.top='0';
              this.UI.bottom='auto';
            }
            if(thisAdvertiseSpaceConfig[i].location=='底部'){
              location='appendChild';
              this.UI.top='auto';
              this.UI.bottom='0';
            } 

            //创建文字与图片高度
            var divHeight="auto";//div高度
            var firstaHeight='auto';//外包裹a高度
            var imgHeight='200px';//图片高度
            var thisDiv=document.createElement("div");//创建div
            var firsta=document.createElement("a");//创建第一个a

             //创建第一个a里面的text
            if(thisAdvertiseSpaceConfig[i].advertiseList[0].text){
             var firsttext=document.createElement("span");
             firsttext.setAttribute("style","text-align:left;width:90%;color:#000;overflow:hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp:"+this.UI.textLine+";-webkit-box-orient: vertical; z-index:"+this.UI.zindex);
             firsttext.innerHTML=thisAdvertiseSpaceConfig[i].advertiseList[0].text;
             firsta.appendChild(firsttext);
            }else{
              textHeight='0px';
            }

             if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==1){
              var oneImgMargin='0';
              //悬浮的大图变小图
               if(thisAdvertiseSpaceConfig[i].type=='悬浮' && parseInt(imgHeight.slice(0,-2))>100){
                imgHeight="100px";
                var oneImgMargin="5px 0px -5px 0px";
              }

              var firstimg=document.createElement("img");
              firstimg.setAttribute("style","margin:"+oneImgMargin+";width:100%;height:"+imgHeight+";z-index:"+this.UI.zindex);
              firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[0]);
              firsta.appendChild(firstimg);
              }else if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==2){
                imgHeight='80px';
               
              //创建第一个a里面的第一个img
              var firstimg=document.createElement("img");
              firstimg.setAttribute("style","margin:1px;width:49%;height:"+imgHeight+";z-index:"+this.UI.zindex);
              firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[0]);
              firsta.appendChild(firstimg);
             // //创建第一个a里面的第二个img
              var firstimg2=document.createElement("img");
              firstimg2.setAttribute("style","margin:1px;width:49%;height:"+imgHeight+";z-index:"+this.UI.zindex);
              firstimg2.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[1]);
              firsta.appendChild(firstimg2);
               console.log(firsta)
              }else if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==3){
                imgHeight='75px';
              //创建第一个a里面的第n个img
              for(var k=0;k<thisAdvertiseSpaceConfig[i].advertiseList[0].img.length;k++){ 
             var firstimg=document.createElement("img");
             firstimg.setAttribute("style","margin:1px;width:32%;height:"+imgHeight+";z-index:"+this.UI.zindex);
             firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[k]);
             firsta.appendChild(firstimg);
              }
                  
              }
              else{
             throw new Error("图片数目1~3！");
              }

              //第一个a属性
             firsta.setAttribute("href",thisAdvertiseSpaceConfig[i].advertiseList[0].link);
             firsta.setAttribute("target",'view_window');
             firsta.setAttribute("id",'aopen');
             firsta.setAttribute("style","text-align:center;text-decoration:none !important;display:block;border-bottom:1px solid #ccc;width:100%;height:"+firstaHeight+";");

            //div 属性 
             thisDiv.setAttribute("style","z-index:"+this.UI.zindex+";padding:"+this.UI.padding+";background-color:"+this.UI.backgroundColor+";position:"+this.UI.position+";left:"+this.UI.left+";top:"+this.UI.top+";bottom:"+this.UI.bottom+";height:"+divHeight+";width:"+this.UI.width+";margin:"+this.UI.margin);
             thisDiv.setAttribute('id',"ui");
            
             //把a导入div,把div导入body
             thisDiv.appendChild(firsta);
             var bodyNode=document.getElementsByTagName("body")[0];
             //bodyNode.appendChild(thisDiv);

             //动态添加
             bodyNode[location](thisDiv,bodyNode.childNodes[0]);
             //创建第二个a
             if(thisAdvertiseSpaceConfig[i].close){ 
             var seconda=document.createElement("a");
             seconda.innerHTML="×";
             seconda.setAttribute("href",'javascript:;');
             seconda.setAttribute("id",'aclose');
             seconda.setAttribute("style","position:absolute;top:0;right:1px;border:1px solid #ccc; background-color:#ccc;color:white;height:20px;width:20px;line-height:20px;text-align:center;font-size:22px;text-decoration:none;");
             thisDiv.appendChild(seconda);
             }
    }//移动端 end
      //PC端
      else if(thisAdvertiseSpaceConfig[i].platform=="PC端"){
        //pc端初始化
        this.UI.width='960px';
        this.UI.height='150px';
        this.UI.left='0';
        this.UI.top='0';
        this.UI.margin='auto';
        this.UI.padding='2px';
         var location;
          if(thisAdvertiseSpaceConfig[i].type=='内嵌'){
            this.UI.zindex='9999';
            this.UI.position='relative';
            if(thisAdvertiseSpaceConfig[i].location=='顶部'){
              location='insertBefore';
            }
            if(thisAdvertiseSpaceConfig[i].location=='底部'){
              location='appendChild';
            }
          }

            if(thisAdvertiseSpaceConfig[i].type=='悬浮'){
            this.UI.position='fixed';
               this.UI.zindex='10002';
               this.UI.left='100%';
               this.UI.width='300px';
               this.UI.height='300px';
            if(thisAdvertiseSpaceConfig[i].location=='顶部'){
              location='insertBefore';
            }
            if(thisAdvertiseSpaceConfig[i].location=='底部'){
              location='appendChild';
              this.UI.top="100%";
              this.UI.margin="-"+this.UI.height+" -300px";
            }       
          }

            //创建div
             var thisDiv=document.createElement("div");
             thisDiv.setAttribute("style","border-bottom:1px solid #ccc;background-color:"+this.UI.backgroundColor+";position:"+this.UI.position+";left:"+this.UI.left+";top:"+this.UI.top+";height:"+this.UI.height+";width:"+this.UI.width+";margin:"+this.UI.margin+";z-index:"+this.UI.zindex+";margin-bottom:10px;");
             thisDiv.setAttribute('id',"ui");
             
               

              for(var k=0;k<thisAdvertiseSpaceConfig[i].advertiseList.length;k++){                 
                if(thisAdvertiseSpaceConfig[i].advertiseList.length<=3){
                        var width="";
                        var height="";
                        if(thisAdvertiseSpaceConfig[i].advertiseList.length==1){
                            width="98%";
                            height="85%";
                        }
                         if(thisAdvertiseSpaceConfig[i].advertiseList.length==2){
                           width="477px";
                            height="82%";
                         }
                          if(thisAdvertiseSpaceConfig[i].advertiseList.length==3){
                           width="318px";
                            height="82%";
                         }
                        //有图片
                      if(thisAdvertiseSpaceConfig[i].advertiseList[k].img && thisAdvertiseSpaceConfig[i].advertiseList[k].img.length>0){
                   var  firsta=document.createElement("a");
                          firsta.setAttribute("href",thisAdvertiseSpaceConfig[i].advertiseList[k].link);
                        firsta.setAttribute("id",'aopen');
                      firsta.setAttribute("style","padding:"+this.UI.padding+";text-decoration:none !important;box-sizing:border-box;display:inline-block;width:"+width+";height:100%;");
                       var firstimg=document.createElement("img");
                       firstimg.setAttribute("style","padding:"+this.UI.padding+";width:100%;height:"+height+";z-index:"+this.UI.zindex);
                       firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[k].img[0]);
                       var firsttext=document.createElement("span");
                     firsttext.setAttribute("style","overflow:hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical; text-align:center;width:100%;height:15%;color:#000;z-index:"+this.UI.zindex);
                     firsttext.innerHTML=thisAdvertiseSpaceConfig[i].advertiseList[k].text;
                       firsta.appendChild(firstimg);
                     firsta.appendChild(firsttext);
                     //把a导入div,把div导入body
                  thisDiv.appendChild(firsta);
                      }else{
                        //没图片
                       var  firstspan=document.createElement("span");
                      firstspan.setAttribute("style","vertical-align:middle;line-height:30px;padding:"+this.UI.padding+";box-sizing:border-box;display:inline-block;width:"+width+";height:280px;");
                        if(thisAdvertiseSpaceConfig[i].advertiseList[k].length>5||thisAdvertiseSpaceConfig[i].advertiseList[k].length<1){
                          throw new Error("文字链数目1~5！");
                        }
                        for(var y=0;y<thisAdvertiseSpaceConfig[i].advertiseList[k].length;y++){  
                       var firsta=document.createElement("a");
                     firsta.setAttribute("style","overflow:hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical; text-decoration:none;font-size:18px;width:100%;color:#000;z-index:"+this.UI.zindex);
                     firsta.innerHTML=thisAdvertiseSpaceConfig[i].advertiseList[k][y].text;
                     firsta.setAttribute("href",thisAdvertiseSpaceConfig[i].advertiseList[k][y].link);
                     firsta.setAttribute("id",'aopen');
                     firstspan.appendChild(firsta);
                   }
                     //把a导入div,把div导入body
                      thisDiv.appendChild(firstspan);
                      }

                        }
                    else{
                    throw new Error("图片数目1~3！");
                         }

                 
               }
 
             
             var bodyNode=document.getElementsByTagName("body")[0];
             //bodyNode.appendChild(thisDiv);
             //动态添加
             bodyNode[location](thisDiv,bodyNode.childNodes[0]);
             //创建第二个a 
            if(thisAdvertiseSpaceConfig[i].close){
             var seconda=document.createElement("a");
             seconda.innerHTML="×";
             seconda.setAttribute("href",'javascript:;');
             seconda.setAttribute("id",'aclose');
             seconda.setAttribute("style","position:absolute;top:-22px;right:1px;border:1px solid #ccc; background-color:#ccc;color:white;height:20px;width:20px;line-height:20px;text-align:center;font-size:22px;text-decoration:none;");
             thisDiv.appendChild(seconda);
            }

      }//PC端 end
      } 

             //监听第二个a的事件，删除整个div
            // var _this=this;
          var acloseAll = document.querySelectorAll("#ui #aclose");
            for( var j = 0 ;j<acloseAll.length;j++ ){
             acloseAll[j].addEventListener('click',function(){
                     this.parentNode.remove();
              },false);
             }

    
            //监听第一个
             var aopenAll = document.querySelectorAll("#ui #aopen");
             var aopenAllBackgroundColor;
            for( var j = 0 ;j<aopenAll.length;j++ ){
             aopenAll[j].addEventListener('mouseover',function(){
                    // this.parentNode.remove();
                   
                    aopenAllBackgroundColor=this.style.backgroundColor;
                    this.style.backgroundColor='#f1eff0';
              },false);
             aopenAll[j].addEventListener('mouseout',function(){
                    // this.parentNode.remove();
                    this.style.backgroundColor=aopenAllBackgroundColor;
              },false);
             }
            }//prototype end

    };

    return advertiseSpace;
}());
  //工具方法
 /**
   * 时间戳转yyyy-MM-dd hh:mm:ss
   * 
   */
  function timeStampToDate(timeStamp){
    var date = new Date(timeStamp);
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds(); 
  return Y+M+D+h+m+s; 
  }
  /**
**复制对象
*/
function cloneObj(oldObj) { 
if (typeof(oldObj) != 'object') return oldObj;
if (oldObj == null) return oldObj;
var newObj = new Object();
for (var i in oldObj)
newObj[i] = cloneObj(oldObj[i]);
return newObj;
};
/**
**扩展对象
*/
function extendObj() { 
var args = arguments;
if (args.length < 2) return;
var temp = cloneObj(args[0]); //调用复制对象方法
for (var n = 1; n < args.length; n++) {
for (var i in args[n]) {
temp[i] = args[n][i];
}
}
return temp;
}
/**
**对象转数组
*/
function transform(obj){
    var arr = [];
    for(var item in obj){
        arr.push(obj[item]);
    }
    return arr;
}

/**
   * cookie
   * 
   */
  // 写cookie
  function setCookie (name,value,expires)
  {
  var exp = new Date();
  exp.setTime(exp.getTime() + expires*1000);
  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
  }
  // 读取cookie
  function getCookie (name)
  {
  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
  if(arr=document.cookie.match(reg))
  return unescape(arr[2]);
  else
  return null;
  }
  // 删除cookie
  function delCookie(name)
  {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval=myUtils.getCookie(name);
  if(cval!=null)
  document.cookie= name + "="+cval+";expires="+exp.toGMTString();
  }

  //ajax

    function myAjax(options) {
        options = options || {};
        options.type = (options.type || "GET").toUpperCase();
        options.dataType = options.dataType || "json";
        options.async==false?options.async:options.async=true;
        var params = formatParams(options.data);
        //创建 - 非IE6 - 第一步
        if (window.XMLHttpRequest) {
            var xhr = new XMLHttpRequest();
        } else { //IE6及其以下版本浏览器
            var xhr = new ActiveXObject('Microsoft.XMLHTTP');
        }

        //接收 - 第三步
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                var status = xhr.status;
                if (status >= 200 && status < 300) {
                    options.success && options.success(xhr.responseText, xhr.responseXML);
                } else {
                    options.fail && options.fail(status);
                }
            }
        }

        //连接 和 发送 - 第二步
        if (options.type == "GET") {
            xhr.open("GET", options.url + "?" + params, options.async);
            xhr.send(null);
        } else if (options.type == "POST") {
            xhr.open("POST", options.url, options.async);
            //设置表单提交时的内容类型
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(params);
        }
    //格式化参数
    function formatParams(data) {
        var arr = [];
        for (var name in data) {
            arr.push(encodeURIComponent(name) + "=" + encodeURIComponent(data[name]));
        }
        arr.push(("v=" + Math.random()).replace(".",""));
        return arr.join("&");
    }
    }

  //滚动条在Y轴上的滚动距离
function getScrollTop(){
　　var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
　　if(document.body){
　　　　bodyScrollTop = document.body.scrollTop;
　　}
　　if(document.documentElement){
　　　　documentScrollTop = document.documentElement.scrollTop;
　　}
　　scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
　　return scrollTop;
}
//文档的总高度
function getScrollHeight(){
　　var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
　　if(document.body){
　　　　bodyScrollHeight = document.body.scrollHeight;
　　}
　　if(document.documentElement){
　　　　documentScrollHeight = document.documentElement.scrollHeight;
　　}
　　scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
　　return scrollHeight;
}
//浏览器视口的高度
function getWindowHeight(){
　　var windowHeight = 0;
　　if(document.compatMode == "CSS1Compat"){
　　　　windowHeight = document.documentElement.clientHeight;
　　}else{
　　　　windowHeight = document.body.clientHeight;
　　}
　　return windowHeight;
}
window.onscroll = function(){
　　if(getScrollTop() + getWindowHeight() == getScrollHeight()){
　　　　//alert("you are in the bottom!");
        //console.log(channelAdvertiseWap)
　　}
};

//对象调用
//var oldAdvertiseSpace=new advertiseSpace();
//oldAdvertiseSpace.getAdvertiseSpaceDiv();
  window.advertiseSpace= advertiseSpace;

})(window);