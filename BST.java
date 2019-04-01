import java.util.Collections;

public class BST<T extends Comparable<T>>
{
	private BSTNode root;

	//Recursive method find returns true if element is in BST.
	public boolean find (Comparable value)
	{
		return find (root, value);
	}	
	private boolean find (BSTNode<T> node, Comparable value)
	{
		if (node == null)		//Element is not in BST.
		{
			return false;
		}
		if (node.get_data().compareTo(value) == 0)		//Found element.
		{
			return true;
		}
		else if (node.get_data().compareTo(value) <= 0)		//Element is greater than what was found.
		{
			return find(node.get_right(), value);
		}
		else 	//Element is smaller than what was found.
		{
			return find(node.get_left(), value);
		}
	}

	//Insert method recursively inserts a new element in the BST.
	public void insert (Comparable value)
	{
		root = insert (root, value);
	}
	private BSTNode<T> insert (BSTNode<T> node, Comparable value)
	{
		if (node == null)		//Creates a new node.
		{
			BSTNode<T> n = new BSTNode<T> (value);
			return n;
		}
		else if (node.get_data().compareTo( value) <=  0)	//Inserts towards the right subtree (the larger elements compared to root).
		{
			node.set_right(insert(node.get_right(), value));
		}
		else 	//Inserts towards left subtree.
		{
			node.set_left(insert(node.get_left(), value));
		}
		return node;
	}

	//Print method recursively prints elements in BST using INORDER traversal.
	public void print()
	{
		print (root);
	}
	private void print (BSTNode<T> node)
	{
		if (node != null)
		{
			print (node.get_left());				//Left
			System.out.println (node.get_data());	//This
			print (node.get_right());				//Right
		}
	}

	//Delete method recursively deletes an element from BST.
	public void delete (Comparable value)
	{
		root = delete (root, value);
	}
	private BSTNode<T> delete (BSTNode<T> node, Comparable value)
	{
		if (node == null)		//If can't find node.
		{
			return null;
		}
		if (node.get_data().compareTo (value) == 0)		//If found node to delete.
		{
			if (node.get_left() == null)		//If node has 0 or 1 child.
			{
				return node.get_right();
			}
			else if (node.get_right() == null)
			{
				return node.get_left();
			}
			else 		//If both left and right child node exists.
			{
				if (node.get_right().get_left() == null)		//If left child of right child node exists.
				{
					node.set_data(node.get_right().get_data());		//Deletes the node by shifting up elements on right.
					node.set_right(node.get_right().get_right());
					return node;
				}
				else 
				{
					node.set_data(removeSmallest (node.get_right()));		//Removes and returns smallest element in subtree.
					return node;
				}
			}
		}
		else if (value.compareTo(node.get_data()) < 0)
		{
			node.set_left(delete (node.get_left(), value));
			return node;
		}
		else
		{
			node.set_right(delete (node.get_right(), value));
			return node;
		}
	}

	//RemoveSmallest method finds and removes the smallest element in BST.
	public Comparable removeSmallest (BSTNode<T> node)
	{
		if (node.get_left().get_left() == null)		//If find node with the left-most leaf.
		{
			Comparable smallest = node.get_left().get_data();		//Copies smallest element.
			node.set_left(node.get_left().get_right());			//Deletes smallest element.
			return smallest;
		}
		else
		{
			return removeSmallest (node.get_left());		//Keeps going down left to find the smallest.
		}
	}

	//Iterator class with data elements data, left, & right, and accessors & mutators.
	private class BSTNode<T extends Comparable<T>>
	{
		public Comparable data;
		public BSTNode<T> left;
		public BSTNode<T> right;

		public BSTNode (Comparable newData)
		{
			data = newData;
			left = null;
			right = null;
		}
		public void set_left (BSTNode<T> left)
		{
			this.left = left;
		}
		public void set_right (BSTNode<T> right)
		{
			this.right = right;
		}
		public BSTNode<T> get_left ()
		{
			return left;
		}
		public BSTNode<T> get_right ()
		{
			return right;
		}
		public void set_data (Comparable newData)
		{
			data = newData;
		}
		public Comparable get_data ()
		{
			return data;
		}
	}
}