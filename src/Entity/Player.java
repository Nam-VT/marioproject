package Entity;

import Main.MainPanel;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends entity {
	MainPanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(MainPanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - gp.titleSize / 2;
		screenY = gp.screenHeight / 2 - gp.titleSize / 2;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width=32;
		solidArea.height=32; 

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.titleSize * 23;
		worldY = gp.titleSize * 21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {

		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	boolean isJumping = false;
	int jumpHeight = 100;
	int currentJumpHeight = 0;
	int initialY = 2;
	int gravity = 5;
	int jumpSpeed = 7;
	public void update() {
		if(keyH.upPressed == true ||  keyH.leftPressed == true || keyH.rightPressed == true) {
			spriteCounter++;
			if(spriteCounter > 5) {
				if(spriteNum1 == 1) {
					spriteNum1 = 2;
				}
				else {
					spriteNum1 = 1;
				}
				spriteCounter = 0;
			}
		}
		if (keyH.upPressed) {
			if (!isJumping) {
				isJumping = true;
				currentJumpHeight = 0;
			}
		}
		if (keyH.leftPressed) {
			direction = "left";
			
		}

		if (keyH.rightPressed) {
			direction = "right";
			
		}
		
		//Check tile collision
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//If collision is false, player can move
		if(collisionOn == false ) {
			if(direction == "up") {
				
			}
			if(direction == "left") {
				worldX -= speed;
			}
			if(direction == "right") {
				worldX += speed;
			}
			
		}
		
		if (isJumping) {
			worldY -= jumpSpeed;
			currentJumpHeight += jumpSpeed;
			if (currentJumpHeight >= jumpHeight || worldY <= 0) {
				isJumping = false;
			}
		} else {
			if(worldY < gp.titleSize * 23) {
				worldY += gravity;
			}
		}
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.WHITE);
//
//		g2.fillRect(x, y, gp.titleSize, gp.titleSize);
		BufferedImage image = null;

		if (direction == "up" && spriteNum1 == 1) {
			image = up1;
		}
		if (direction == "up" && spriteNum1 == 2) {
			image = up2;
		}
		if (direction == "down" && spriteNum1 == 1) {
			image = down1;
		}
		if (direction == "down" && spriteNum1 == 2) {
			image = down2;
		}
		if (direction == "left" && spriteNum1 == 1) {
			image = left1;
		}
		if (direction == "left" && spriteNum1 == 2) {
			image = left2;
		}
		if (direction == "right" && spriteNum1 == 1) {
			image = right1;
		}
		if (direction == "right" && spriteNum1 == 2) {
			image = right2;
		}

		g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
	}
}
