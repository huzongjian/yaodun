<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-CN">

  <head>
  <jsp:include page="layout.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
     <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="" />
        <meta name="author" content="stilearning" />
  <!--      <link href="http://fonts.googleapis.com/css?family=Aclonica:regular" rel="stylesheet" type="text/css" />  -->
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        

 </head>
<body>
        <!-- section header -->
        <header class="header" data-spy="affix" data-offset-top="0">
            <!--nav bar helper-->
            <div class="navbar-helper">
                <div class="row-fluid">
                    <!--panel site-name-->
                    <div class="span2">
                        <div class="panel-sitename">
                            <h2><a href="index.html"><span class="color-teal">Shy</span>Shine</a></h2>
                        </div>
                    </div>
                    <!--/panel name-->
                </div>
            </div><!--/nav bar helper-->
        </header>
        
        <!-- section content -->
        <section class="section">
            <div class="container">
                <div class="signin-form row-fluid">
        <div class="span3"></div>
                    <div class="span5" align="center">
                        <div class="box corner-all">
                            <div class="box-header grd-green color-white corner-top">
                                <span>Create an account!</span>
                            </div>
                            <div class="box-body bg-white">
                                <form id="sign-up" method="post" action="/yaodun/User/add">
                                    <div class="control-group">
                                        <label class="control-label">用户名：</label>
                                        <div class="controls">
                                            <input type="text" placeholder="User Name" class="input-block-level" data-validate="{required: true, messages:{required:'请输入用户名'}}" name="userName" id="userName" autocomplete="off" />
                                             <div align="center" style="color: red">${result}</div>
                                            <p class="help-block muted helper-font-small">2到20个字符，支持英文、数字和"_"格式</p>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Email</label>
                                        <div class="controls">
                                            <input type="text" placeholder="EmailAddress" class="input-block-level" data-validate="{required: true, email:true, messages:{required:'请输入Email Address', email:'请输入正确的Email Address'}}" name="email" id="email" autocomplete="off" />
                                            <p class="help-block muted helper-font-small"><strong>注意：</strong> 你将收到确认电子邮件.</p>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">密码</label>
                                        <div class="controls">
                                            <input type="password" placeholder="Password" class="input-block-level" data-validate="{required: true, minlength: 6, messages:{required:'请输入密码', minlength:'请输入至少6位密码'}}" name="password" id="password" autocomplete="off" />
                                            <p class="help-block muted helper-font-small">6到20个字条，只能包含数字.大小写及标点</p>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">确认密码</label>
                                        <div class="controls">
                                            <input type="password" placeholder="Confirmation Password" class="input-block-level" data-validate="{required: true, equalTo: '#password', messages:{required:'请输入确认密码', equalTo: '确认密码与密码不一致'}}" name="password_again" id="password_again" autocomplete="off" />
                                            <p class="help-block muted helper-font-small">再次输入您的密码。</p>
                                        </div>
                                    </div>
                                   <!--  <div class="control-group">
                                        <p class="term-of-use">Lorem ipsum dolor sit amet, natoque per at morbi at vestibulum leo, velit non, curabitur ac est. <a href="#">terms of use</a> and <a href="#">privacy policy</a>.</p>
                                    </div> -->
                                    <div class="control-group">
                                        <label class="checkbox">
                                            <input type="checkbox" data-form="uniform"  name="subscribe" id="subscribe_yes" value="yes" checked="" /> Sign me up for the newsletter
                                        </label>
                                    </div>
                                    <div class="form-actions">
                                        <input type="submit" class="btn btn-block btn-large btn-success" value="提   交" />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div><!--/Sign Up-->
                </div><!-- /row -->
            </div><!-- /container -->
            
            <!-- modal recover -->
            <div id="modal-recover" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-recoverLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 id="modal-recoverLabel">Reset password <small>Username Or Email Address</small></h3>
                </div>
                <div class="modal-body">
                    <form id="form-recover" method="post" >
                        <div class="control-group">
                        
                            <div class="controls">
                                <input type="text" data-validate="{required: true, email:true, messages:{required:'Please enter field email', email:'Please enter a valid email address'}}" name="recover" />
                                <p class="help-block helper-font-small">Enter your username or email address and we will send you a link to reset your password.</p>
                            </div>
                             
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                    <input type="submit" form="form-recover" class="btn btn-primary" value="Send reset link" />
                </div>
            </div><!-- /modal recover-->
        </section>

       
  <script type="text/javascript">
        
            $(document).ready(function() {
                //使ie能用placeholder;
                initPlaceHolders();
                
                 // try your js
                // uniform
                 $('[data-form=uniform]').uniform();
                
                // validate
                $('#sign-up').validate();
                $('#form-recover').validate();
                //前景图片变化
                
                 var location = (window.location+'').split('/');  
                var basePath = location[0]+'//'+location[2]+'/'+location[3];  

            	$.backstretch([
            			        basePath+"/resources/img/bg/1.jpg",
            			        basePath+"/resources/img/bg/2.jpg",
            			        basePath+"/resources/img/bg/3.jpg",
            			        basePath+"/resources/img/bg/4.jpg"
            			        ], {
            			          fade: 750,
            			          duration: 4000
            			    });
                
            }); 
        </script>
    </body>
</html>
