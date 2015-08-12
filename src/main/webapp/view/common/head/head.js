/**
 * 鼠标单击在一级菜单跳转不同的页面
 * @auther honghao
 */
function clickMenuForword(index)
{
		// 个人中心
		if(index == 1)
		{
			window.location.href = "/gbdbas/view/personalcenter/homepage/homePage.jsp";
		}
		//查看权限
		else if(index==2)
		{
			window.location.href = "/gbdbas//view/personalcenter/viewright/findUserRight.jsp";
		}
		//我的收藏
		else if(index==3)
		{
			window.location.href = "/gbdbas/view/personalcenter/favorites/myFavorites.jsp";
		}	
		//登录日志
		else if(index==4)
		{
			window.location.href = "/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp";
		}
		//子账号管理
		else if(index==5)
		{
			window.location.href = "/gbdbas/view/usermanagement/sonUser.jsp";
		}
		//贸易区情报
		else if(index==6)
		{
			window.location.href = "/gbdbas/view/datasearch/datasearch.jsp";
		}
		//我的客户
		else if(index==7)
		{
			window.location.href = "/gbdbas/view/alldb/customer/customer.jsp";
		}
		//我的对手
		else if(index==8)
		{
			window.location.href = "/gbdbas/view/alldb/competitor/competitor.jsp";
		}
		//产品标签
		else if(index==9)
		{
			window.location.href = "/gbdbas/view/alldb/projectanalyze/projectanalyze.jsp";
		}
		//对比分析
		else if(index==10)
		{
			window.location.href = "/gbdbas/view/contrastreport/jsp/reportContrastre.jsp";
		}
		//全库下载
		else if(index==11)
		{
			window.location.href = "/gbdbas/view/alldb/downloadDBData/downLoadAllData.jsp";
		}
		//产品定制
		else if(index==12)
		{
			window.location.href = "/gbdbas/view/sellcenter/sellIndex.jsp";
		}
		//客服中心
		else if(index==13)
		{
			window.location.href = "/gbdbas/view/servicecenter/jsp/customercenter.jsp";
		}
}