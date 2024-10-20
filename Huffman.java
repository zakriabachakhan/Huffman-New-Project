package huffmanAlgo;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Huffman {

	
	static Tree Tree; 	static Tree Node;	
	static String Binary_converted_code = "";	

	public static void main(String[] args) throws IOException {

		String binary_Code = "";
		
		ArrayList<Character> List = new ArrayList<Character>();
				int size_of_File = find_size_of_text_file();
		char []characterInputFile = new char[size_of_File]; 
		
        FileReader inputTextFile = new FileReader("C:\\Users\\expert\\Desktop\\input.txt");
        
        int i;
        int temp=0; 
        
        System.out.println("\nLetter in the text file");
        while ((i = inputTextFile.read()) != -1)
        {
        	
            System.out.print((char)i);
            binary_Code= binary_Code+(char)i; 
            characterInputFile[temp] = (char)i; 
            temp++; 
        }
		
		for ( i = 0; i < characterInputFile.length; i++) {
			if (!(List.contains(characterInputFile[i]))) {
				List.add(characterInputFile[i]);
			}
		}
		int[] Array_Character = new int[List.size()];
		for (int x = 0; x < Array_Character.length; x++) {
			Array_Character[x] = 0;
		}
		for ( i = 0; i < List.size(); i++) {
			char is_binary_tree = List.get(i);
			for (int x = 0; x < characterInputFile.length; x++) {
				if (is_binary_tree == characterInputFile[x]) {
					Array_Character[i]++;
				}
			}
		}
		for ( i = 0; i < Array_Character.length - 1; i++) {
			for (int j = 0; j < Array_Character.length - 1; j++) {
				if (Array_Character[j] < Array_Character[j + 1]) {
					int swapVar = Array_Character[j];
					Array_Character[j] = Array_Character[j + 1];
					Array_Character[j + 1] = swapVar;

					char swapVars = List.get(j);
					List.set(j, List.get(j + 1));
					List.set(j + 1, swapVars);
				}
			}
		}

		
		System.out.println("\nFrequency of Letter\n");
		for ( i = 0; i < Array_Character.length; i++) {
			System.out.println(List.get(i) + " - " + Array_Character[i]);
		}

		Tree rootNode = null;
		Tree currentNode = null;
		Tree lastNode = null;

		for (i = 0; i < Array_Character.length; i++) {
			Tree Tree = new Tree(List.get(i).toString(), Array_Character[i]);
			if (rootNode == null) {
				rootNode = Tree;
				lastNode = Tree;
			} else {
				currentNode = rootNode;
				while (currentNode.ptrFront != null) {
					currentNode = currentNode.ptrFront;
				}
				currentNode.ptrFront = Tree;
				currentNode.ptrFront.ptrFrontBack = currentNode;
				lastNode = Tree;
			}
		}

		
		make_Tree_From_Huffman(rootNode, lastNode);

		System.out.println("\nBinary Tree\n");
		
		System.out.println();
		show_Inorder_Traversing(Tree);

		
		System.out.println();
		show_PreOrder_Traversing(Tree);

		char[] binary_CodeArray = binary_Code.toCharArray();
		char is_binary_tree;
		
		

		for ( i = 0; i < binary_CodeArray.length; i++) {
			currentNode = Tree;
			is_binary_tree = binary_CodeArray[i];
			String Binarycode = "";
			while (true) 
			{
				if (currentNode.left_Node.word_For_String.toCharArray()[0] == is_binary_tree)
				{
					Binarycode += "0";
			
					break;
				} 
				else 
				{
					Binarycode += "1";
					if (currentNode.right_Node != null)
					{
						if (currentNode.right_Node.word_For_String.toCharArray()[0] == List.get(Array_Character.length - 1)) {
							break;
						}
						currentNode = currentNode.right_Node;
					} else {
						break;
					}
					
				}
				
			}
			Binary_converted_code+=Binarycode;
			
		}
		System.out.println();
		System.out.println("\n==============================================\n");
		System.out.println("Binary Code For Letter\n" + Binary_converted_code);
		
		
        String text  = Binary_converted_code; 
 
        Path fileName = Path.of("C:\\Users\\expert\\Desktop\\outputText.txt");
 
       
        Files.writeString(fileName, text);
 
        
        String file_content = Files.readString(fileName);
		System.out.println("\n----------------------------------------------------------\n");
        
        System.out.println(file_content+"\nshow encoding code of letter in text file\n");
		
		
        System.out.println("\n---------------------------------------------------------------\n");
        
        System.out.println("\n result of decoding the encoding text\n"); 
        
        char []decodeText = Binary_converted_code.toCharArray();
        for(int k=0;k<binary_CodeArray.length;k++)
        {
        	System.out.println(binary_CodeArray[k]); 
        }
		
		// write to file
	}


    
	
	
	public static  int find_size_of_text_file() throws IOException {

        
    
        FileReader fr = new FileReader("C:\\Users\\expert\\Desktop\\input.txt");
        
    
        int i;
        int size_of_character=0;
    
        while ((i = fr.read()) != -1)
 
        {
            
            size_of_character++; 
    }
        return size_of_character; 
    }
	
		public static  char[] get_Data_From_File(int size_of_File) throws IOException {

        
        
        FileReader get_data_from_file = new FileReader("C:\\Users\\expert\\Desktop\\input.txt");
        
        
        int i;
        char[] CharArray = new char[size_of_File]; 
        
        while ((i = get_data_from_file.read()) != -1)
 
        {
           
        	CharArray [i] = (char)i; 
    }
        return CharArray ; 
    }

	
	public static void show_PreOrder_Traversing(Tree head) {

		
		if (head != null) {

			System.out.print(head.word_For_String + "->");
			show_PreOrder_Traversing(head.left_Node);
			show_PreOrder_Traversing(head.right_Node);

		}

	}

	
	public static void show_Inorder_Traversing(Tree head) {

		if (head != null) {
			show_Inorder_Traversing(head.left_Node);
			System.out.print(head.word_For_String + "->");
			show_Inorder_Traversing(head.right_Node);
		}

	}

	public static void make_Tree_From_Huffman(Tree root, Tree end) {
		Tree = new Tree(end.ptrFrontBack.word_For_String + end.word_For_String, end.ptrFrontBack.data
				+ end.data);
		Tree.left_Node = end.ptrFrontBack;
		Tree.right_Node = end;
		end.ptrFrontBack.ptrFrontBack.ptrFront = Tree;
		Tree.ptrFrontBack = end.ptrFrontBack.ptrFrontBack;
		end = Tree;
		end.ptrFront = null;
		Tree current = root;

		while (current.ptrFront != null) {
			System.out.print(current.word_For_String + "->");
			current = current.ptrFront;
		}

		System.out.println(current.word_For_String);

		if (root.ptrFront == end) {
			Tree = new Tree(root.word_For_String + end.word_For_String, root.data + end.data);
			Tree.left_Node = root;
			Tree.right_Node = end;
			Tree.ptrFront = null;
			Tree.ptrFrontBack = null;
			System.out.println(Tree.word_For_String);
			Node = Tree;
		} else {
			make_Tree_From_Huffman(root, end);
		}
	}

}
class Tree {

	
	String word_For_String;
	int data;
	Tree left_Node;
	Tree right_Node;
	Tree ptrFront;
	Tree ptrFrontBack;

	
	Tree(String word_For_String, int data) {

		this.word_For_String = word_For_String;
		this.data = data;
		this.left_Node = null;
		this.right_Node = null;
		this.ptrFront = null;
		this.ptrFrontBack = null;

	}

}