/**
 * 
 */

import java.util.LinkedList;

/**
 * @author Dany
 *
 */
public class SeperateChainingWithTwoChoice {

	/**
	 * @param args
	 */
	public static int count=0;
	public int tableSize;
	LinkedList<WrapObject>[] arrList;
	public static class WrapObject
	{
		
		Long key;
		Long value;
		public WrapObject(Long key, Long value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public SeperateChainingWithTwoChoice()
	{
		
		tableSize=10001;
		arrList=new LinkedList[tableSize];
		
	}
	

	
	public int hashFirst(Long key, int tableSize)
	{
        Long tabSize = Long.parseLong(String.valueOf(tableSize));


        Long hashVal =  key % tabSize;
        if( hashVal < 0 )
            hashVal += tableSize;

        return Integer.parseInt(String.valueOf(hashVal));
	}
	public int hashSecond(Long key, int tableSize)
	{
		int hashVal = 0;

        String keyStr=String.valueOf(key);
        for( int i = 0; i < keyStr.length(); i++ )
            hashVal = 47 * hashVal + keyStr.charAt( i );

        hashVal %= tableSize;
        if( hashVal < 0 )
            hashVal += tableSize;

        return hashVal;
	}
	public void insert(Long key, Long value)
	{
		if(find(key)==null)
		{
			if(arrList[hashFirst(key, tableSize)]==null)
			{

			LinkedList<WrapObject> lLinkedList = new LinkedList<WrapObject>();
			lLinkedList.addLast(new WrapObject(key, value));
			arrList[hashFirst(key, tableSize)] = lLinkedList;
			}else
			{
				LinkedList<WrapObject> lLinkedList1 = arrList[hashFirst(key, tableSize)];
				lLinkedList1.addLast(new WrapObject(key, value));
				arrList[hashFirst(key, tableSize)] = lLinkedList1;
			}
		}else
		{
			LinkedList<WrapObject> lList = arrList[ hashFirst(key, tableSize) ];
			for(WrapObject obj : lList)
			{
				if(obj.key.compareTo(key)==0)
				{
					obj.value = value;
					break;
				}
			}
			arrList[ hashFirst(key, tableSize) ] = lList;
		}
	}
	
	public Long find(Long key)
	{
		if(arrList[hashFirst(key, tableSize)]==null)
		{
			return null;
		}
		LinkedList<WrapObject> lList = arrList[ hashFirst(key, tableSize) ];
		for(WrapObject obj : lList)
		{

			if((obj.key).compareTo(key)==0)
			{
				return obj.value;
			}//else
				//return null;
			//break;
			
		}
		return null;

		
	}
	public boolean remove(Long key)
	{
		if(find(key)==null)
			return false;
		LinkedList<WrapObject> lList = arrList[ hashFirst(key, tableSize) ];
		for(WrapObject obj : lList)
		{
			if(obj.key.compareTo(key)==0)
			{
				lList.remove(obj);
				arrList[ hashFirst(key, tableSize) ]= lList;
				return true;
			}
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SeperateChainingWithTwoChoice H = new SeperateChainingWithTwoChoice();
/*        final Long NUMS = 4000L;
        final Long GAP  =   37L;*/

        H.insert(486258446706086943L, 40993179849633107L);
       /* H.insert(25L, 23464L);
        H.insert(26L, 23767464L);
        
        H.remove(26L);*/


        System.out.println(H.find(486258446706086943L));
      /*  System.out.println(H.find(25L));
        System.out.println(H.find(26L));*/

        
        /*System.out.println( "Checking... (no more output means success)" );

        for( Long i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( i, i );
        for( Long i = 1L; i < NUMS; i+= 2 )
            H.remove(( i ));

        for( Long i = 2L; i < NUMS; i++ )
            if( ((H.find( ( i ) ))) != i )
            {
                System.out.println( "Find fails " + i );
            }

        for( Long i = 1L; i < NUMS; i+=2 )
        {
            if( H.find( ( i ) ) != null )
                System.out.println( "OOPS!!! " +  i  );
        }*/

	}
	
	
}
