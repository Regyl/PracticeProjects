;������������ ������ �3
;�������� ��. ��. ���-311 ������� �������
;������� 16
;R=DH, i=2, j=1, k=0
.486
.model flat, stdcall
option casemap: none
.stack 100h
;=========================================
include c:/masm32/include/windows.inc
include c:/masm32/include/user32.inc
include c:/masm32/include/kernel32.inc
includelib c:/masm32/lib/user32.lib
includelib c:/masm32/lib/kernel32.lib
;=========================================
.data
a db '�'
b db '�'
i db 0100B
j db 0010B
k db 0001B
rez db ?,?
tit db ' ��������� '
;=========================================
.code
main:
;1) (A and b) or b => DH +
mov dh, a ;��������� � dh �������� a
and dh, b ;�������� �������� �� b
or dh, b  ;��������� b

;=========================================
;2) ���� i ��� �������� R = 0, �������� 1 � k ��� �������� R ����� 0 +
test dh, i ;��������� �� �����, ��������� ������ ZF
jnz notNull ;������� ���� � ���������� �� 0
or dh, k   ;1 � k ���
jmp nextStep ;������� � ���������� ����

notNull:
mov al, k
not al ;��������� � al �����, �������� k
and dh, al ;������ � dh, ����������� �� �����

;=========================================
;3) �������� j ���� � ������� ������������ ���
nextStep:
xor dh, j;
;=========================================
;4) �������� ������� ����� �� +
ror dh, 4
;=========================================
;5) dh<32? => dh=dh+32 +
cmp dh, 32 
jae finalStep
add dh, 32
;=========================================
;6) ������ � rez ���������� � �����
finalStep: 
mov rez, dh;������ ���� ������� � rez
INVOKE MessageBox, 0, OFFSET rez, OFFSET tit, 0
end main