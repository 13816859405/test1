package cn.bdqn.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




import cn.bdqn.entity.EasyBuyProduct;
import cn.bdqn.entity.ShoppingCart;

import cn.bdqn.service.EasyBuyPorductService;
import cn.bdqn.util.PageBean;

public class ProductServlet extends HttpServlet {
	private EasyBuyPorductService easyBuyPorductService=new EasyBuyPorductService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro=request.getParameter("pro");
		if(pro.equals("findProduct")){
			String epIdStr=request.getParameter("epId");
			if(epIdStr!=null){
				Integer epId=Integer.parseInt(epIdStr);
				EasyBuyProduct products=easyBuyPorductService.findById(epId);
				//����Ʒ����cookie���������ckprolist
				Cookie ckprolist=null;
				//����֮ǰ��cookie
				Cookie[] cke=request.getCookies();
				for(Cookie cookie:cke){
					if("ckprolist".equals(cookie.getName())){
						ckprolist=cookie;//�ҵ���
						//�Ѷ�Ӧ��idƴ�ӵ�value
						ckprolist.setValue(ckprolist.getValue()+epId+",");
						break;
					}
				}
				if(ckprolist==null){
					//����cookie
					ckprolist=new Cookie("ckprolist",epId+",");
				}
				//ͨ��response���cookice
				response.addCookie(ckprolist);
				
				request.setAttribute("products", products);
				request.getRequestDispatcher("product-view.jsp").forward(request, response);
			}
		}else if(pro.equals("proList")){
			String epcIdStr=request.getParameter("epcId");
			if(epcIdStr!=null){
				Integer epcId=Integer.parseInt(epcIdStr);
				String pageNoStr=request.getParameter("pageNo");
				int pageNo=1;
				if(pageNoStr!=null){
					pageNo=Integer.parseInt(pageNoStr);
				}
				int pageSize=8;
				PageBean<EasyBuyProduct> pageBeanList=easyBuyPorductService.findProductList(pageNo, pageSize, epcId);
				request.setAttribute("pageBeanList", pageBeanList);
				request.setAttribute("epcId", epcId);
				request.getRequestDispatcher("product-list.jsp").forward(request, response);
				
			}
		}else if(pro.equals("proPageMan")){
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=4;
			PageBean<EasyBuyProduct> pageBean=easyBuyPorductService.findByProductPage(pageNo, pageSize);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}else if(pro.equals("addProduct")){
			
			String path=request.getSession().getServletContext().getRealPath("images/product");
			request.setCharacterEncoding("UTF-8");
			String epName=null,description=null,pic=null,msg=null;
			Integer epcId=0,stock=0;
			double price=0;
			//ȷ�����ϴ�
			boolean canUpload=ServletFileUpload.isMultipartContent(request);
			if(canUpload){
				//�����ϴ�����
				FileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload fileUpload=new ServletFileUpload(factory);
				fileUpload.setSizeMax(1024*1024*2);//�����ϴ���С
				try {
					List<FileItem> itemList=fileUpload.parseRequest(request);
					for(FileItem item:itemList){
						if(item.isFormField()){
							String file=item.getFieldName();
							if(file.equals("productName")){
								epName=item.getString("UTF-8");
							}else if(file.equals("productDetail")){
								description=item.getString("UTF-8");
							}else if(file.equals("parentId")){
								epcId=Integer.parseInt(item.getString());
							}else if(file.equals("productPrice")){
								price=Double.parseDouble(item.getString());
							}else if(file.equals("productNumber")){
								stock=Integer.parseInt(item.getString());
							}
						}else{
							String file=item.getName();//ȡ��ԭ������·��
							// ���������
							pic=new File(file).getName();
							//��������
							List<String> extList=Arrays.asList("gif","jpg","bmp", "png");
							//��ú�׺
							String suffix=pic.substring(pic.lastIndexOf('.')+1);
							if(extList.contains(suffix)){
								File fileTo=new File(path,pic);
								item.write(fileTo);
							}else{
								System.out.print("��ʽ���ԣ������ϴ���");
							}
						}
					}
					int ret=easyBuyPorductService.addProduct(epName,description,epcId,price,stock,pic);
					if(ret>0){
						msg = "��ϲ�������ɹ���";
					}
				} catch (FileUploadBase.SizeLimitExceededException e) {
					// TODO Auto-generated catch block
					msg = "��Ǹ���ϴ��ļ�̫�󣬲���ʧ�ܣ�";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("manage-result.jsp").forward(request, response);
			}
			
		}else if(pro.equals("showUpate")){
			String epIdStr=request.getParameter("epId");
			if(epIdStr!=null){
				Integer epId=Integer.parseInt(epIdStr);
				EasyBuyProduct product=easyBuyPorductService.findById(epId);
				request.setAttribute("product", product);
				
				request.getRequestDispatcher("product-modify.jsp").forward(request, response);
			}
		}else if(pro.equals("upateProduct")){
			
			String epIdStr=request.getParameter("epId");
			Integer epId=Integer.parseInt(epIdStr);
			String path=request.getSession().getServletContext().getRealPath("images/product");
			request.setCharacterEncoding("UTF-8");
			String epName=null,description=null,pic=null,msg=null;
			Integer epcId=0,stock=0;
			double price=0;
			//ȷ�����ϴ�
			boolean canUpload=ServletFileUpload.isMultipartContent(request);
			if(canUpload){
				//�����ϴ�����
				FileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload fileUpload=new ServletFileUpload(factory);
				fileUpload.setSizeMax(1024*1024*2);//�����ϴ���С
				try {
					List<FileItem> itemList=fileUpload.parseRequest(request);
					for(FileItem item:itemList){
						if(item.isFormField()){
							String file=item.getFieldName();
							if(file.equals("productName")){
								epName=item.getString("UTF-8");
							}else if(file.equals("productDetail")){
								description=item.getString("UTF-8");
							}else if(file.equals("parentId")){
								epcId=Integer.parseInt(item.getString());
							}else if(file.equals("productPrice")){
								price=Double.parseDouble(item.getString());
							}else if(file.equals("productNumber")){
								stock=Integer.parseInt(item.getString());
							}
						}else{
							String file=item.getName();//ȡ��ԭ������·��
							// ���������
							pic=new File(file).getName();
							//��������
							List<String> extList=Arrays.asList("gif","jpg","bmp", "png");
							//��ú�׺
							String suffix=pic.substring(pic.lastIndexOf('.')+1);
							if(extList.contains(suffix)){
								File fileTo=new File(path,pic);
								item.write(fileTo);
							}else{
								System.out.print("��ʽ���ԣ������ϴ���");
							}
						}
					}
					EasyBuyProduct product=easyBuyPorductService.findById(epId);
					product.setEpcId(epcId);
					product.setDescription(description);
					product.setEpName(epName);
					product.setEpId(epId);
					product.setPrice(price);
					product.setStock(stock);
					if(pic!=null){
						product.setFileName(pic);
						}
					int ret=easyBuyPorductService.upateProduct(product);
					if(ret>0){
						msg = "��ϲ�������ɹ���";
					}
					
				} catch (FileUploadBase.SizeLimitExceededException e) {
					// TODO Auto-generated catch block
					msg = "��Ǹ���ϴ��ļ�̫�󣬲���ʧ�ܣ�";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("manage-result.jsp").forward(request, response);
			}
		}else if(pro.equals("delProduct")){
			String epIdStr=request.getParameter("epId");
			if(epIdStr!=null){
				Integer epId=Integer.parseInt(epIdStr);
				int ret=easyBuyPorductService.delProduct(epId);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}else if(pro.equals("shoppingCart")){
			//���ﳵ
			String opr=request.getParameter("opr");
			String epIdStr=request.getParameter("epId");
			int epId=Integer.parseInt(epIdStr);
			ShoppingCart cart=(ShoppingCart)request.getSession().getAttribute("cart");
			
			if(cart==null){
				cart=new ShoppingCart();
			}
			if("add".equals(opr)){
				//����
				EasyBuyProduct product=easyBuyPorductService.findById(epId);
				//�ж� session���Ƿ���cart
				int quantity=1;//�������
				cart.addItem(product, quantity);
			}else if("del".equals(opr)){
				//ɾ��
				cart.removeItem(epId);
			}else if("update".equals(opr)){
				//�޸�
				String numStr=request.getParameter("num");
				int num=Integer.parseInt(numStr);
				cart.modifyQuantity(epId, num);
			}
			request.getSession().setAttribute("cart",cart);
			response.sendRedirect("shopping.jsp");
			//request.getRequestDispatcher("shopping.jsp").forward(request, response);
		}
	}

}
