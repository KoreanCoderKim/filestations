import pygame
import sys
import random

card_data = ['D:\피파 카드깡 프로젝트\프로젝트\BWC호나우두.png','D:\피파 카드깡 프로젝트\프로젝트\ICON호나우두.png','D:\피파 카드깡 프로젝트\프로젝트\LN호나우두.png','D:\피파 카드깡 프로젝트\프로젝트\MC호나우두.png']
data = random.choice(card_data)
pygame.init()
screen = pygame.display.set_mode((220,320))
pygame.display.set_caption("FC CARD")
clock = pygame.time.Clock()
clock.tick(60)
screen.fill((255,255,255))
background = pygame.image.load(data)

#이벤트 루프
running = True #게임 진행 여부에 대한 변수 True : 게임 진행 중
while running:
    for event in pygame.event.get(): #이벤트의 발생 여부에 따른 반복문
        if event.type == pygame.QUIT: #창을 닫는 이벤트 발생했는가?
            running = False

    screen.blit(background, (0, 0)) #배경에 이미지 그려주고 위치 지정
    pygame.display.update()