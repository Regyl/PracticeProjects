; ������� �. �., ���-311
; ������� 16
; ��������� ������� �������������� ���� ��������� ������ ������ ������� �

.486
.model flat, stdcall
option casemap: none
.stack 100h

include C:/masm32/include/kernel32.inc
includelib C:/masm32/lib/kernel32.lib

.data
N equ 4
M equ 5
A db 1,2,3,4,5
  db -6,7,8,9,20
  db 1,13,2,7,4
  db 6,8,2,0,10
B db 4 dup (?)

.code
main:

; EBX - ��������� ����� ������� �
; ECX - ��������� �������� ������� � (� ����������)
; EDI - ��������� �������� ������� �
mov ECX,N ;������� ����� ������� A
mov EBX,0 ;�������� ������ ������ ������� A
mov EDI,0 ;�������� ������� ������� ������� B

row: ;���� �� �������
mov AL,0; ����� ��������� ������
mov EDX,ECX ;EDX ��� ��������� �������� �������� ECX ��� �������� �����
mov ECX,M ;������� �������� ������� A


column: ;���� �� ��������
add AL,A[EBX][ECX-1]
loop column


cbw ;�������������� ������������� AL � ������������ AX (�������)
mov cl, M ; (�������)
div cl ;� AL ������������ ������� ��������������
mov B[EDI],AL

mov ECX,EDX ;EDX ��� ��������� �������� �������� ECX ��� �������� �����
add EBX,M ;������� � ��������� ������ A(+5)
add EDI,TYPE B
loop row

invoke ExitProcess, 0
end main