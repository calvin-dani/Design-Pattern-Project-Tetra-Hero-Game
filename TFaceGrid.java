/**
 *  Grid location on the screen 
 * @author Calvin
 *
 */
public class TFaceGrid 
{
	private int row;
	private int column;
	
	/**
	 *  Constructor
	 */
	public TFaceGrid() {}
	
	/**
	 *  Constructor 
	 * @param row
	 * @param column
	 */
	public TFaceGrid(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	/**
	 *  Set the row number 
	 * @param row
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 *  Set the column number
	 * @param column
	 */
	public void setColumn (int column)
	{
		this.column = column;
	}
	
	/**
	 *  Return the row number
	 * @return int 
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 *  Return the column number 
	 * @return int
	 */
	public int getColumn()
	{
		return column;
	}
	
	
}
