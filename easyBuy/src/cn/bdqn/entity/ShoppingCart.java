package cn.bdqn.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable{
	
	public List<ShoppingCatItem> items=new ArrayList<ShoppingCatItem>();
	
	//��ȡ���ﳵ�е�������Ʒ
	public List<ShoppingCatItem> getItems(){
		return items;
	}
	//���һ��
	public void addItem(EasyBuyProduct product,int quantity){
		for(int i=0;i<items.size();i++){
			//�жϹ��ﳵ���Ƿ����д���Ʒ����������ۼ�����
			if((items.get(i).getProduct().getEpId()==product.getEpId())){
				items.get(i).setQuantity(items.get(i).getQuantity()+quantity);
				return;
			}
		}
		items.add(new ShoppingCatItem(product,quantity));
	}
	
	//�Ƴ�һ��
	public void removeItem(int epId){
		int index=-1;
		for(int i=0;i<items.size();i++){
			//�жϹ��ﳵ���Ƿ����д���Ʒ����������ۼ�����
			if((items.get(i).getProduct().getEpId()==epId)){
				index=i;//�����±�
				break;
			}
		}
		items.remove(index);
	}
	
	//�޸�����
	public void modifyQuantity(int epId,int num){
		int index=-1;
		for(int i=0;i<items.size();i++){
			//�жϹ��ﳵ���Ƿ����д���Ʒ����������ۼ�����
			if((items.get(i).getProduct().getEpId()==epId)){
				index=i;//�����±�
				break;
			}
		}
		items.get(index).setQuantity(items.get(index).getQuantity()+num);
	}
	
	//�����ܼ۸�--�ṩҳ����${totalCost}
	public double getTotalCost(){
		double sum=0;
		for(ShoppingCatItem item:items){
			sum=sum+item.getCost();
		}
		return sum;
	}
	
	public int getTotalQuantity(){
		int sum=0;
		for(ShoppingCatItem item:items){
			sum=sum+item.getQuantity();
		}
		return sum;
	}
}
