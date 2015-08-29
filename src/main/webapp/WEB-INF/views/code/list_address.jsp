<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
<title>地址管理</title>
<!-- PAGE LEVEL STYLE REFERENCES -->
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">地址管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 地址管理
				</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 地址列表 </span></li>
			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-actions">
				<form id="form_search" class="form-horizontal"
					action="/code/address/list" method="GET">
					<div class="form-group">
						<label class="col-md-1 control-label">地址: </label>
						<div class="col-md-3">
							<input type="text" id="groupNameQ"
								class="form-control input-medium input-inline" name="name"
								placeholder="Input group name" value="${seed.filter['nameQ']}" />
						</div>
						<div class="pull-right">
							<a href="javascript:Account.search();" class="btn dark">查询
								<i class="fa fa-search"></i>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="blank-form-actions">
				<a class="btn green" href="#" id="createGroupBatch"><i
					class="fa fa-plus"></i>新建</a> <a class="btn blue" href="#"
					id="deleteGroupBatch"><i class="fa fa-times"></i>删除</a>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-calendar"></i> 地址列表
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<liuliume:pagination position="above"></liuliume:pagination>
					<table
						class="table table-bordered table-striped table-condensed flip-content"
						id="fromGroupTable">
						<thead class="flip-content">
							<tr>
								<th style="width: 42px;">
									<div>
										<span><input type="checkbox" id="selectAll"></span>
									</div>
								</th>
								<th style="width: 10%;">唯一标示</th>
								<th style="width: 20%;">名称</th>
								<th style="width: 10%;">父级标示</th>
                                <th style="width: 20%;">父级名称</th>
								<th style="width: 20%;">级别</th>
                                <th style="width: 20%;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${seed.result }" var="item">
								<tr>
									<td>
										<div class="">
											<span><input class="" type="checkbox" id="test"
												value="${crmSegment.id}"> </span>
										</div>
									</td>
									<td>${item.id}</td>
									<td>${item.name}</td>
									<td>${item.parent_id}</td>
                                    <td>${item.parent_name}</td>
									<td>${item.level}</td>

									<td><a class="btn default btn-xs blue-stripe" href="#"
										name="editGroup" groupId="${crmSegment.id}"><i
											class="fa fa-edit"></i> 修改</a> <a
										class="btn default btn-xs purple-stripe" href="#"
										name="deleteGroup" groupId="${crmSegment.id}"><i
											class="fa fa-times"></i> 删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<liuliume:pagination position="below"></liuliume:pagination>
				</div>
			</div>
		</div>
	</div>

	<!--确认modal，用于确认功能 ，仿照confirm-->
	<div id="confirmModal" class="modal fade" aria-hidden="true"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title" id="confirmModalTitle">Delete Segment</h4>
				</div>
				<div class="modal-body">
					<div class="info" id="confirmModalContent">Please confirm if
						you want to delete the Segment?</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn blue" id="confirmOkBtn">Confirm</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 确认弹出框结束 -->
	<!-- 提示弹出框,用于弹出提示信息，仿照alert-->
	<div id="alertModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title" id="alertModalTitle">Delete Segment</h4>
				</div>
				<div class="modal-body">
					<div class="info" id="alertModalContent">Please choose the
						group to be deleted!</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn blue" data-dismiss="modal"
						id="alertOkBtn">Confirm</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 提示弹出框结束 -->

	<input type="hidden" id="setOptionUrl" value="${ctx}/account/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />
	<!-- PAGE LEVEL JS REFERENCES -->
	<script src="${ctx}/resources/scripts/pages/account/accountList.js"
		type="text/javascript"></script>
</body>