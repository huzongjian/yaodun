<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<jsp:include page="/jsp/layout.jsp"></jsp:include>
<script src="/yaodun/resources/bootstrap-wysiwyg/bootstrap-wysiwyg.js"></script>
<script src="/yaodun/resources/bootstrap-wysiwyg/external/jquery.hotkeys.js"></script>
<link href="/yaodun/resources/bootstrap-wysiwyg/bootstrap-responsive.min.css" rel="stylesheet"/>
<link href="/yaodun/resources/bootstrap-wysiwyg/bootstrap-combined.no-icons.min.css" rel="stylesheet"/>
<link href="/yaodun/resources/bootstrap-wysiwyg/toolbar.css" rel="stylesheet"/>
<script src="/yaodun/resources/bootstrap-fileupload/jquery.ui.widget.js"></script>
<script src="/yaodun/resources/bootstrap-fileupload/jquery.fileupload.js"></script>
<script src="/yaodun/resources/bootstrap-fileupload/jquery.iframe-transport.js"></script>
<script src="/yaodun/resources/bootstrap-fileupload/ajaxfileupload.js"></script>



<link href="/yaodun/resources/bootstrap-fileupload/jquery.fileupload-ui.css" rel="stylesheet"/>
<script src="/yaodun/resources/js/html5shiv.js"></script>
<script src="/yaodun/resources/js/respond.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>Insert title here</title>
</head>
<body>
	<div>

		<div class="navbar-inner">

			<div class="container"></div>
		</div>
	</div>

	<div id="content">
		<div class="container">

			<div class="row">

				<div class="span6">
					<div class="box-body bg-white">

						<table id="table-methods-table" data-height="110%"
							data-show-columns="true" data-search="true"
							data-show-refresh="true" data-show-toggle="true"
							data-pagination="true" data-height="600">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="knowledgeId">编号</th>
									<th data-field="typeName">类型</th>
									<th data-field="title">标题</th>
									<th data-field="type" data-visible="false">类型</th>
									<th data-field="img" data-visible="false">图片名称</th>
									<th data-field="description" data-visible="false">内容</th>
								</tr>
							</thead>
						</table>
						<button id="get-data" class="btn btn-default"
							data-method="getData">Get Data</button>

						<button id="load-data" class="btn btn-default" data-method="load">
							Load Data</button>

					</div>
				</div>
				<!--  -->
				<div class="span6">
					<div class="box-body bg-white">
						<form id="sign-in">
							<div class="control-group">
								<label class="control-label">类型</label>

								<div>
									<input style="display: none;" id="id"> <select
										style="width: 190px" id="type">
										<option value="0" selected="selected">综合</option>
										<option value="1">妇女</option>
										<option value="2">儿童</option>
										<option value="3">老人</option>
										<option value="4">抗生素</option>
									</select>
								</div>

							</div>
							<div class="control-group">
								<label class="control-label">标题</label>
								<div class="controls">
									<div>

										<input type="text" class="input-block-level"
											data-validate="{required: true, messages:{required:'请输入密码'}}"
											name="title" id="title" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">图片名称</label>
								<div class="controls">
									<div>
										<input type="text" placeholder="xx.png/89*89"
											class="input-block-level"
											data-validate="{required: true, messages:{required:'图片名称'}}"
											name="img" id="img" autocomplete="off" />
									</div>
								</div>


								<span class="btn btn-success fileinput-button"> <i
									class="icon-plus icon-white"></i> <span>选择上传文件</span> <input
									id="myfile" type="file" name="myfile">
								</span> <br> <br>



							</div>
							<div class="control-group">
								<label class="control-label">内容</label>
								<div class="controls">
									<div class="btn-toolbar" data-role="editor-toolbar"
										data-target="#editor">
										<div class="btn-group">
											<a class="btn dropdown-toggle" data-toggle="dropdown"
												title="Font"><i class="icon-font"></i><b class="caret"></b></a>
											<ul class="dropdown-menu">
											</ul>
										</div>
										<div class="btn-group">
											<a class="btn dropdown-toggle" data-toggle="dropdown"
												title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
												class="caret"></b></a>
											<ul class="dropdown-menu">
												<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
												<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
												<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
											</ul>
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i
												class="icon-bold"></i></a> <a class="btn" data-edit="italic"
												title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
											<a class="btn" data-edit="strikethrough"
												title="Strikethrough"><i class="icon-strikethrough"></i></a>
											<a class="btn" data-edit="underline"
												title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="insertunorderedlist"
												title="Bullet list"><i class="icon-list-ul"></i></a> <a
												class="btn" data-edit="insertorderedlist"
												title="Number list"><i class="icon-list-ol"></i></a> <a
												class="btn" data-edit="outdent"
												title="Reduce indent (Shift+Tab)"><i
												class="icon-indent-left"></i></a> <a class="btn"
												data-edit="indent" title="Indent (Tab)"><i
												class="icon-indent-right"></i></a>
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="justifyleft"
												title="Align Left (Ctrl/Cmd+L)"><i
												class="icon-align-left"></i></a> <a class="btn"
												data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i
												class="icon-align-center"></i></a> <a class="btn"
												data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i
												class="icon-align-right"></i></a> <a class="btn"
												data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
												class="icon-align-justify"></i></a>
										</div>
										<div class="btn-group">
											<a class="btn dropdown-toggle" data-toggle="dropdown"
												title="Hyperlink"><i class="icon-link"></i></a>
											<div class="dropdown-menu input-append">
												<input class="span2" placeholder="URL" type="text"
													data-edit="createLink" />
												<button class="btn" type="button">Add</button>
											</div>
											<a class="btn" data-edit="unlink" title="Remove Hyperlink"><i
												class="icon-cut"></i></a>

										</div>

										<div class="btn-group">
											<a class="btn" title="Insert picture (or just drag & drop)"
												id="pictureBtn"><i class="icon-picture"></i></a> <input
												type="file" data-role="magic-overlay"
												data-target="#pictureBtn" data-edit="insertImage" />
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
												class="icon-undo"></i></a> <a class="btn" data-edit="redo"
												title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
										</div>
										<input type="text" data-edit="inserttext" id="voiceBtn"
											x-webkit-speech="">
									</div>

									<div id="editor"
										style="height: 300px; overflow: scroll; border: solid"
										class="input-block-level">Go ahead&hellip;</div>
								</div>
							</div>
							<div class="form-actions">
								<input class="btn btn-block btn-large btn-primary" value="提交"
									id="commit" />

							</div>
						</form>
					</div>

				</div>

			</div>
		</div>
	</div>




</body>
<script type="text/javascript">
	$(function() {
		
	
		$table = $('#table-methods-table').bootstrapTable({
			 method: 'get',
             url: '/yaodun/Knowledge/editKnowledge',
			//data : getRows()
		}).on('click-row.bs.table', function (e, row, $element) {
			$("#id").val(row.knowledgeId);
			$("#type").val(row.type);
			$("#title").val(row.title);
			$("#img").val(row.img);
			$("#editor").html(row.description);
            $result.text('Event: click-row.bs.table, data: ' + JSON.stringify(row));
        });

		$('#get-selections').click(	function() {
					alert('Selected values: '+ JSON.stringify($table.bootstrapTable('getSelections')));
		});

		$('#get-data').click(function() {
			alert('current data: '+ JSON.stringify($table.bootstrapTable('getData')));
			});
		$('#load-data, #append-data, #check-all, #uncheck-all, #show-loading, #hide-loading').click(function() {							
			$table.bootstrapTable($(this).data('method'), getRows());
		});
		
		//富文本编辑器
		function initToolbarBootstrapBindings() {
		      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
		            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
		            'Times New Roman', 'Verdana'],
		            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
		      $.each(fonts, function (idx, fontName) {
		          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
		      });
		      $('a[title]').tooltip({container:'body'});
		    	$('.dropdown-menu input').click(function() {return false;})
				    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
		        .keydown('esc', function () {this.value='';$(this).change();});

		      $('[data-role=magic-overlay]').each(function () { 
		        var overlay = $(this), target = $(overlay.data('target')); 
		        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
		      });
		      $('#voiceBtn').hide();
		      // if ("onwebkitspeechchange"  in document.createElement("input")) {
		      //   var editorOffset = $('#editor').offset();
		      //   $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
		      // } else {
		      //   $('#voiceBtn').hide();
		      // }
		    };
		    initToolbarBootstrapBindings();  
		    $('#editor').wysiwyg();
		    window.prettyPrint && prettyPrint();
		//end 富文本编辑器
		$("#commit").click(function(){
			var baseinfo={};
			baseinfo["id"]=$("#id").val();
			baseinfo["type"]=$("#type").val();
			baseinfo["title"]=$("#title").val();
			baseinfo["img"]=$("#img").val();
			baseinfo["desciption"]=$("#editor").html();
			var b =JSON.stringify(baseinfo);
			$.ajaxFileUpload({  
				   type: "POST",  
				   url: "/yaodun/Knowledge/upload",  
				   data: {"b":b},
				   datatype : "json",
				   fileElementId:"myfile",
				   success: function(msg){
					   alert(msg.msg);
					    $("#id").val("");
						$("#type").val(0);
						$("#title").val("");
						$("#img").val("");
						$("#editor").html("");
						 $table.bootstrapTable('refresh', {
			                    url: '/yaodun/Knowledge/editKnowledge'
			                });

				   },
				   error:function(data){
					   alert("error");
				   }
				  
				}); 

			
			
		});
		
		
		
	});
</script>
</html>