package kan.number2.views;

import kan.number2.Helpers.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Number extends Actor {

	public TextureRegion _texture;
	
	private int _number;
	
	private float _width, _height, _x, _y;
	
	public int _row, _col;
	
	private MapStage _stage;
	
	public Number(MapStage stage, int number, int row, int col, float width, float height) {
		updateNumber(number);
		_stage = stage;
		_width = width;
		_height = height;
		updatePosition(row, col);
	}
	
	public void updatePosition(int row, int col) {
		_row = row;
		_col = col;
		_x = _stage.translateX(_col);
		_y = _stage.translateY(_row);
		setBounds(_x, _y, _width, _height);
	}
	
	public int getValue() {
		return _number;
	}
	
	 public boolean moveUp() {

         if (_row <= 0)
             return false;

         int tempRow = _row - 1;

         boolean binded = false;
         while (tempRow >= 0) {
             Number v = _stage.getNumber(tempRow, _col);
             if (v != null) {
                 if (v._number == _number) {
                     //StartMove//Update the Value
                     _stage.setNumber(_row, _col, null);
                     stepMove(_row, _col, tempRow, _col, _number * 2, _stage.getNumber(tempRow, _col));
                     _row = tempRow;
                     //updateNumber(_number * 2);
                     _stage.updateScore(_number * 2);
                     _number *= 2;
                     binded = true;
                     //_stage.removeNumber(_row, _col);
                     _stage.setNumber(_row, _col, this);
                     return true;
                 }
                 else {
                     binded = true;
                     if (_row > tempRow + 1) {
                         _stage.setNumber(_row, _col, null);
                         stepMove(_row, _col, tempRow + 1, _col, _number, null);
                         _row = tempRow + 1;
                         _stage.setNumber(_row, _col, this);
                         return true;
                     }
                     return false;
                 }
             }

             tempRow--;
         }

         if (!binded) {
             _stage.setNumber(_row, _col, null);
             stepMove(_row, _col, 0, _col, _number, null);
             _row = 0;
             _stage.setNumber(_row, _col, this);
             android.util.Log.i("xxx", "New: " + _row + " " + _col);
             
             
             return true;
         }
         
         return false;
     }

     public boolean moveLeft() {

         if (_col <= 0)
             return false;

         int tempCol = _col - 1;

         boolean binded = false;
         while (tempCol >= 0) {
             Number v = _stage.getNumber(_row, tempCol);
             if (v != null) {
                 if (v._number == _number) {
                     //StartMove//Update the Value
                     _stage.setNumber(_row, _col, null);
                     stepMove(_row, _col, _row, tempCol, _number * 2, _stage.getNumber(_row, tempCol));
                     _col = tempCol;
                     _stage.updateScore(_number * 2);
                     _number *= 2;
                     binded = true;
                     //_stage.removeNumber(_row, _col);
                     _stage.setNumber(_row, _col, this);
                     
                     return true;
                 } else {
                     binded = true;
                     if (_col > tempCol + 1) {
                         _stage.setNumber(_row, _col, null);
                         stepMove(_row, _col, _row, tempCol + 1, _number, null);
                         _col = tempCol + 1;
                         _stage.setNumber(_row, _col, this);
                         return true;
                     }
                     return false;
                 }
             }

             tempCol--;
         }

         if (!binded) {
             _stage.setNumber(_row, _col, null);
             stepMove(_row, _col, _row, 0, _number, null);
             _col = 0;
             _stage.setNumber(_row, _col, this);
             return true;
         }
         
         return false;
     }

     public boolean moveDown() {

         if (_row >= 3)
             return false;

         int tempRow = _row + 1;

         boolean binded = false;
         while (tempRow <= 3) {
             Number v = _stage.getNumber(tempRow, _col);
             if (v != null) {
                 if (v._number == _number) {
                     //StartMove//Update the Value
                     _stage.setNumber(_row, _col, null);
                     stepMove(_row, _col, tempRow, _col, _number*2, _stage.getNumber(tempRow, _col));
                     _row = tempRow;
                     _stage.updateScore(_number * 2);
                     _number *= 2;
                     binded = true;
                     //_stage.removeNumber(_row, _col);
                     _stage.setNumber(_row, _col, this);
                     
                     return true;
                 } else {
                     binded = true;
                     if (_row < tempRow - 1) {
                         _stage.setNumber(_row, _col, null);
                         stepMove(_row, _col, tempRow - 1, _col, _number, null);
                         _row = tempRow - 1;
                         _stage.setNumber(_row, _col, this);
                         return true;
                     }
                     return false;
                 }
             }

             tempRow++;
         }

         if (!binded) {
             _stage.setNumber(_row, _col, null);
             stepMove(_row, _col, 3, _col, _number, null);
             _row = 3;
             _stage.setNumber(_row, _col, this);
             
             return true;
         }
         
         return false;
     }

     public boolean moveRight() {

         if (_col >= 3)
             return false;

         int tempCol = _col + 1;

         boolean binded = false;
         while (tempCol <= 3) {
             Number v = _stage.getNumber(_row, tempCol);
             if (v != null) {
                 if (v._number == _number) {
                     //StartMove//Update the Value
                     _stage.setNumber(_row, _col, null);
                     stepMove(_row, _col, _row, tempCol, _number*2, _stage.getNumber(_row, tempCol));
                     _col = tempCol;
                     _stage.updateScore(_number * 2);
                     _number *= 2;
                     binded = true;
                     //_stage.removeNumber(_row, _col);
                     _stage.setNumber(_row, _col, this);
                     
                     return true;
                 } else {
                     binded = true;
                     if (_col < tempCol - 1) {
                         _stage.setNumber(_row, _col, null);
                         stepMove(_row, _col, _row, tempCol -1, _number, null);
                         _col = tempCol - 1;
                         _stage.setNumber(_row, _col, this);
                         return true;
                     }
                     return false;
                 }
             }

             tempCol++;
         }

         if (!binded) {
             _stage.setNumber(_row, _col, null);
             stepMove(_row, _col, _row, 3, _number, null);
             _col = 3;
             _stage.setNumber(_row, _col, this);
             return true;
         }
         
         return false;
     }

	
	public int stepMove(final int oldRow, final int oldCol, final int newRow, final int newCol, final int newValue, final Number removedNum) {
		if(newCol < 0 || newRow < 0) {
			return 0;
		}
		
		if(oldRow == newRow && oldCol == newCol)
			return 0;
		
		_stage.startMove();
		
		float newX = _stage.translateX(newCol);
		float newY = _stage.translateY(newRow);
		float duration = 0.06f * Math.max(Math.abs(newRow - oldRow), Math.abs(newCol - oldCol));
		
		//Actions.
		final SequenceAction _sequenceAction = Actions.sequence();
		_sequenceAction.addAction(Actions.moveTo(newX, newY, duration));
		_sequenceAction.addAction(Actions.run(new Runnable() {
			@Override
			public void run() {
				Number.this.clearActions();
				
				if(removedNum != null) {
					_stage.removeNumber(removedNum);
					_stage.setNumber(newRow, newCol, Number.this);
				}
				updateNumber(newValue);
				_stage.stopMove();
			}
			
		}) );
		
		this.addAction(_sequenceAction);
		return 1;
	}
	
	public void updateNumber(int newNum) {
		_number = newNum;
		assignTexture();
	}
	
	private void assignTexture() {
		
		switch(_number) {
		case 2:
			_texture = Assets.N2;
			break;
		case 4:
			_texture = Assets.N4;
			break;
		case 8:
			_texture = Assets.N8;
			break;
		case 16:
			_texture = Assets.N16;
			break;
		case 32:
			_texture = Assets.N32;
			break;
		case 64:
			_texture = Assets.N64;
			break;
		case 128:
			_texture = Assets.N128;
			break;
		case 256:
			_texture = Assets.N256;
			break;
		case 512:
			_texture = Assets.N512;
			break;
		case 1024:
			_texture = Assets.N1024;
			break;
		case 2048:
			_texture = Assets.N2048;
			break;
		case 4096:
			_texture = Assets.N4096;
			break;
		case 0:
			_texture = Assets.NEmpty;
			break;
		}
	}
	
	@Override
	public void act(float arg0) {
		super.act(arg0);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		float scale = 1.0f;
		batch.draw(_texture, getX(), getY(), _width/2, _width/2, _width, _height, scale, scale, 0);
	}

}
