package dp.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * 两个方法互相调用构成递归
 * @author zyh
 *
 */
public class BST2 {
	public static void main(String[] args) {
		BST2 bst2 = new BST2();
		bst2.generateTrees(4);
	}
	public List<TreeNode> generateTrees(int n) {
		List<TreeNode> trees =  preTree(1, n);
		System.out.println(trees.size());
		return trees;
	}
	
	/**
	 * 给定根节点的值 和 左右子树节点的取值范围，
	 * 返回给定值为根节点的森林
	 * @param root
	 * @param leftFrom
	 * @param leftTo
	 * @param rightFrom
	 * @param rightTo
	 * @return
	 */
	private List<TreeNode> recursivlyGT(int root, int leftFrom, int leftTo, int rightFrom, int rightTo) {
		List<TreeNode> trees = new ArrayList<TreeNode>();
		List<TreeNode> leftPreTrees = new ArrayList<TreeNode>();
		List<TreeNode> rightPreTrees = new ArrayList<TreeNode>();
		
		if(leftFrom <= leftTo){
			// from == to
			leftPreTrees = preTree(leftFrom, leftTo);
		}
		if(rightFrom <= rightTo){
			rightPreTrees = preTree(rightFrom, rightTo);
		}
		
		int leftSize = leftPreTrees.size();
		int rightSize = rightPreTrees.size();
		
		if(leftSize == 0) {
			for(int j = 0; j < rightSize; j++) {
				TreeNode rootTreeNode = new TreeNode(root);
				rootTreeNode.right = rightPreTrees.get(j);
				trees.add(rootTreeNode);
			}
		} else if(rightSize == 0) {
			for(int i = 0; i < leftSize; i++) {
				TreeNode rootTreeNode = new TreeNode(root);
				rootTreeNode.left = leftPreTrees.get(i);
				trees.add(rootTreeNode);
			}
		} else {
			for(int i = 0; i < leftSize; i++) {
				for(int j = 0; j < rightSize; j++) {
					TreeNode rootTreeNode = new TreeNode(root);
					rootTreeNode.left = leftPreTrees.get(i);
					rootTreeNode.right = rightPreTrees.get(j);
					trees.add(rootTreeNode);
				}
			}
		}
		return trees;
	}
	
	/**
	 * 按顺序给出元素的值
	 * 返回这些元素构成的森林
	 * @param from
	 * @param to
	 * @return
	 */
	private List<TreeNode> preTree(int from, int to) {
		List<TreeNode> preTrees = new ArrayList<TreeNode>();
		if(from == to) {
			preTrees.add(new TreeNode(from));
		} else {
			
			for(int i = from; i <= to; i++) {
				// i 作为根节点值
				// 1, 1, 0, 2, 4
				// 4, 1, 3, 5, 4
				preTrees.addAll(recursivlyGT(i, from, i - 1, i + 1, to));
			}
		}
		
		return preTrees;
	}
}

// 树节点
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}