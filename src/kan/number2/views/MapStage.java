package kan.number2.views;

import java.util.LinkedList;
import java.util.Queue;

import kan.number2.MyApplication;
import kan.number2.R;
import kan.number2.Helpers.Assets;
import kan.number2.Helpers.Helper;
import kan.number2.Helpers.RunnableWrapper;
import android.widget.Toast;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class MapStage extends Stage {
	
	private final int DIMENSION = 4;
	
	private Number[][] _backNumbers = new Number[DIMENSION][DIMENSION];
	private Number[][] _numbers = new Number[DIMENSION][DIMENSION];
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	private float _NumberWidth, _NumberHeight;
	
	int PADDINGLEFT = 28;
	int PADDINGRIGHT = 28;
	
	private Image _glowImage = new Image();
	
	private Score _score = new Score();;
	
	private boolean _isGameOver = false;
	
	public MapStage () {
		super(WIDTH, HEIGHT);;
		
		//initBgActor();
		
		initNumbers();
		
		for(int i = 0; i < 2; i++)
			newNumber();
		this.addActor(_score);
	}
	
	public float translateX(int col) {
		return PADDINGLEFT + col * _NumberWidth;
	}
	
	public float translateY(int row) {
		return 440 - row * _NumberHeight;
	}
	
	boolean exsitsEmptySlot() {
		for(int i = 0; i < DIMENSION; ++i) {
			for(int j = 0 ;j < DIMENSION; ++j) {
				if(_numbers[i][j] == null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	boolean newNumber() {
		boolean _2 = MathUtils.randomBoolean();
		int newNum = _2 ? 2 : 4; 
		
		if(exsitsEmptySlot()) {
			int dimension;
			do {
				dimension = MathUtils.random(0, DIMENSION * DIMENSION-1);
			} while(_numbers[dimension/DIMENSION][dimension % DIMENSION] != null);
			
			translate(dimension, newNum);
			return true;
		}

		return false;
	}
	
	void translate(int no1, int num) {
		int i = no1 / DIMENSION;
		int j = no1 % DIMENSION;
		
		if(_numbers[i][j] == null) {
			_numbers[i][j] = new Number(this, num, i, j, _NumberWidth, _NumberHeight);
			this.addActor(_numbers[i][j]);
			//_numbers[i][j].updateNumber(num);
		}
	}
	
	void initBgActor() {
		_glowImage.setDrawable(new TextureRegionDrawable(Assets.NumberSelected));
		_glowImage.setBounds(-20, 5, 360, 352);
		this.addActor(_glowImage);
	}
	
	public void reset() {
		initNumbers();
		newNumber();
		newNumber();
		_score.setScore(0);
		_isGameOver = false;
		this._runningAnims = 0;
	}
	
	void initNumbers() {
		_NumberWidth = (WIDTH - PADDINGLEFT - PADDINGRIGHT) / DIMENSION;
		_NumberHeight = _NumberWidth;
		for(int i = 0; i < DIMENSION; ++i) {
			for(int j = 0 ;j < DIMENSION; ++j) {
				_backNumbers[i][j] = new Number(this,0, i, j, _NumberWidth, _NumberHeight);
				this.addActor(_backNumbers[i][j]);
				
				_numbers[i][j] = null;
			}
		}
	}

	float startX, startY;
	public void touchDown(float x, float y) {
		startX = x;
		startY = y;
		Buttons.touchDown(this, x, y);
	}
	
	public enum Direction {
		Null(0), Left(1), Right(2), Up(3), Down(4);
		
		int _i;
		Direction(int i){
			_i = i;
		}
	}
	
	public void touchUp(float x, float y) {
		if(Buttons.touchUp(x, y))
			return;
		
		if(_isGameOver) {
			return;
		}
		
		float minDistance = 2;
		Direction direction = Direction.Null;
		if(x == startX) {
			float distance = y - startY;
			if(distance >= minDistance) {
				direction = Direction.Up;
			} else if(distance <= -minDistance){
				direction = Direction.Down;
			}
		} else {
			float rtan = (y-startY) / (x-startX);
			if(y >= startY && x > startX) {
				if(rtan < 1 && x - startX > minDistance) {
					direction = Direction.Right;
				} else if(rtan > 1 && y - startY > minDistance) {
					direction = Direction.Up;
				}
			} else if(y>= startY && x < startX) {
				if(-rtan < 1 && startX - x > minDistance) {
					direction = Direction.Left;
				} else if(-rtan > 1 && y - startY > minDistance) {
					direction = Direction.Up;
				}
			} else if(y <= startY && x > startX) {
				if(-rtan < 1 && x - startX > minDistance) {
					direction = Direction.Right;
				} else if(-rtan > 1 && startY -y > minDistance) {
					direction = Direction.Down;
				}
			} else if(y <= startY && x < startX) {
				if(rtan < 1 && startX-x > minDistance) {
					direction = Direction.Left;
				} else if(rtan > 1 && startY -y > minDistance) {
					direction = Direction.Down;
				}
			}
		}
		
		refreshNumbers(direction);
	}
	
	
	int debugValue(int i, int j) {
		return (_numbers[i][j] == null ? 0 : _numbers[i][j].getValue());
	}
	
	void debug() {
		
		for(int x = 0; x < DIMENSION; ++x) {
			android.util.Log.i("xxx", debugValue(x, 0) + "\t" + debugValue(x, 1) +"\t"
					+ debugValue(x, 2) +"\t"+ debugValue(x, 3) +"\t");
		}
	}
	
	void updateScore(int score) {
		
		_score.setScore(_score.getScore() + score); 
		
		if(score == 512 || score == 1024) {
			MyApplication.context.setAdVisible(true);
		} else if(score == 2048) {
			MyApplication.context.setAdVisible(true);
			Assets.winSound.play();
			MyApplication.context.handler.post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(MyApplication.context, 
							R.string.tips_2048,  
							Toast.LENGTH_LONG).show();
				}
				
			});
			
		} else if(score == 4096) {
			Assets.winSound.play();
			_isGameOver = true;
			Helper.showSuccessDialog(this);
		}
	}
	
	
	int getValue(int i, int j) {
		if(i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION) {
			if(_numbers[i][j] != null)
				return _numbers[i][j].getValue();
		}
		
		return -1;
	}
	
	void removeNumber(Number number) {
		this.getActors().removeValue(number, true);
	}
	
	void removeNumber(int i, int j) {
		Number number = getNumber(i,j);
		if(number != null) {
			this.getActors().removeValue(number, true);
		}
	}
	
	Number getNumber(int i, int j) {
		if(i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION) {
			return _numbers[i][j];
		}
		
		return null;
	}
	
	 public boolean moveLeft() {
		 boolean bMoved = false;
         for (int i = 0; i < 4; ++i) {
             for (int j = 0; j < 4; ++j) {
                 if(_numbers[i][j] != null)
                     if(_numbers[i][j].moveLeft())
                    	 bMoved = true;
             }
         }
         
         return bMoved;
     }

     public boolean moveUp() {
    	 boolean bMoved = false;
         for (int i = 0; i < 4; ++i) {
             for (int j = 0; j < 4; ++j) {
                 if (_numbers[i][j] != null)
                     if(_numbers[i][j].moveUp())
                    	 bMoved = true;
             }
         }
         
         return bMoved;
     }

     public boolean moveDown() {
    	 boolean bMoved = false;
         for (int i = 3; i >= 0; --i) {
             for (int j = 3; j >= 0; --j) {
                 if (_numbers[i][j] != null)
                     if(_numbers[i][j].moveDown())
                    	 bMoved = true;
             }
         }
         
         return bMoved;
     }

     public boolean moveRight() {
    	 boolean bMoved = false;
         for (int i = 3; i >= 0; --i) {
             for (int j = 3; j >= 0; --j) {
                 if (_numbers[i][j] != null)
                     if(_numbers[i][j].moveRight())
                    	 bMoved = true;
             }
         }
         
         return bMoved;
     }

     public void setNumber(int row, int col, Number number) {
         _numbers[row][col] = number;
     }
	
	boolean isExsitsEquals(int i, int j) {
		int value = _numbers[i][j].getValue();
		
		return value == getValue(i-1, j) ||
			value == getValue(i+1, j) ||
			value == getValue(i, j -1) ||
			value == getValue(i, j + 1);
	}
	
	int _runningAnims = 0;
	
	boolean doMove(Direction direction) {
		boolean isMoved = false;
		
		if(direction == Direction.Down)
			isMoved = moveDown();
		else if(direction == Direction.Up)
			isMoved = moveUp();
		else if(direction == Direction.Left)
			isMoved = moveLeft();
		else if(direction == Direction.Right)
			isMoved = moveRight();
		else
			isMoved = false;
		
		debug();
		return isMoved;
	}
	
	int _animations = 0;
	
	boolean _moveing = false;
	
	void startMove() {
		_animations ++;
	}
	
	void stopMove() {
		if(!_moveing)
			return;
		
		--_animations;
		if(_animations == 0) {
			_moveing = false;
			
			newNumber();
			
			if(!exsitsEmptySlot()) {
				boolean existSame = false;
				for(int i = 0; i < DIMENSION; ++i) {
					for(int j = 0; j < DIMENSION; ++j) {
						if(isExsitsEquals(i, j)) {
							existSame = true;
							break;
						}
					}
					
					if(existSame) {
						break;
					}
				}
				
				if(!existSame) {
					Helper.showFailedDialog(MapStage.this);
					_isGameOver = true;
					//this.reset();
				}
			}
		}
	}
	
	void refreshNumbers(Direction direction) {
		
		if(_moveing )
			return;
		_animations = 0;
		
		if(doMove(direction)) {
			_moveing= true;
			Assets.moveSound.play();
		} else {
			Assets.noMoveSound.play();
		}
	}
	
	@Override
	public void act(float arg0) {
		super.act(arg0);
	}

	@Override
	public void draw() {
		super.draw();
	}
}
