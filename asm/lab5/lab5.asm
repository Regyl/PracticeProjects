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
counter PROC
    push EBP
    mov EBP,ESP ;������������ ��������������� ��������� ����� ebp=esp
    push ECX ;������� ������������ � ��������� ��������
    push EBX
    mov AL,0
    mov ECX,[EBP+8]
    mov EBX,[EBP+12]
    column:
        add AL,[EBX]
        inc EBX
    loop column

    cbw ;�������������� ������������� AL � ������������ AX (�������)
    mov cl, M ; (�������)
    div cl ;� AL ������������ ������� ��������������

    pop EBX
    pop ECX
    pop EBP
    RET 100
counter ENDP

main:
mov ECX,N ;������� ����� ������� A
mov EDI,0 ;�������� ������� ������� ������� B

; ���������� � ������� �����
lea EBX,A
mov EDX,M
row: ;���� �� �������
    push EBX ;�������� 1: ����� ������ ������� �
    push EDX ;�������� 2: ����� ��������� � ������ ������� �

    CALL counter

    mov B[EDI],AL
    add EBX,M ;������� � ��������� ������
    add EDI,TYPE B
loop row

invoke ExitProcess, 0
end main