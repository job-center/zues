<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>宠物管理</title>
<!-- PAGE LEVEL STYLE REFERENCES -->
<script type="text/javascript"
	src="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2-metronic.css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">宠物管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 宠物管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span> 宠物管理 </span></li>

			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<form class="form-horizontal form-row-seperated" action="#"
				id="IndexForm" enctype="multipart/form-data" method="post">
				<div class="portlet">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-calendar"></i>添加宠物
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">宠物名称: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="animalsName"
												id="animalsName" placeholder="请输入宠物类型"
												value="${animals.animalsName}"> <span
												class="help-block display-hide" id="errorInfoName">请输入宠物名称!</span>
											<span class="label label-warning"> 最长32个字符. </span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">宠物种类:<span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<select
												class="table-group-action-input form-control input-medium select2me"
												name="typeId" id="typeId">
												<c:forEach var="type" items="${allTypes}">
													<option value="${type.id}"
														<c:choose>
															<c:when test='${empty animals }'>
																<c:if test='${type.id eq -1}'>selected</c:if>
															</c:when>
															<c:otherwise>
																<c:if test='${type.id eq animals.type.id}'>selected</c:if>
															</c:otherwise>
														</c:choose>>
														${type.typeName }</option>
												</c:forEach>
											</select> <span class="help-block display-hide" id="errorInfoType">请选择种类!</span>
										</div>
									</div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">宠物首字母分类: <span
                                                class="required"> * </span>
                                        </label>
                                        <div class="col-md-10">
                                            <select
                                                    class="table-group-action-input form-control input-medium select2me"
                                                    name="orderType" id="orderType">
                                                <option value="">请选择</option>
                                                <option value="A">A</option>
                                                <option value="B">B</option>
                                                <option value="C">C</option>
                                                <option value="D">D</option>
                                                <option value="E">E</option>
                                                <option value="F">F</option>
                                                <option value="G">G</option>
                                                <option value="H">H</option>
                                                <option value="I">I</option>
                                                <option value="J">J</option>
                                                <option value="K">K</option>
                                                <option value="L">L</option>
                                                <option value="M">M</option>
                                                <option value="N">N</option>
                                                <option value="O">O</option>
                                                <option value="P">P</option>
                                                <option value="Q">Q</option>
                                                <option value="R">R</option>
                                                <option value="S">S</option>
                                                <option value="T">T</option>
                                                <option value="U">U</option>
                                                <option value="V">V</option>
                                                <option value="W">W</option>
                                                <option value="X">X</option>
                                                <option value="Y">Y</option>
                                                <option value="Z">Z</option>
                                            </select> <span class="help-block display-hide" id="error_orderType">请输入宠物分类!!</span>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-md-2 control-label">宠物排序: <span
                                                class="required"> * </span>
                                        </label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="orderTypeOrderBy"
                                                   id="orderTypeOrderBy" placeholder="请输入宠物排序"
                                                   value="${animals.orderTypeOrderBy}"> <span
                                                class="help-block display-hide" id="error_orderTypeOrderBy">请输入宠物排序!</span>
                                            <span class="label label-warning"> 请填写数字. </span>
                                        </div>
                                    </div>



									<div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="id" name="id" value="${animals.id}" />
											<a href="" class="btn green" id="btnConfirm"
												name="btnConfirm">确定</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>


	<input type="hidden" id="setOptionUrl" value="${ctx}/account/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/animal/animalIndex.js"
		type="text/javascript"></script>

    <script type="application/javascript">
        $("#orderType option[value=${animals.orderType}]").attr("selected",true);
    </script>
</body>

