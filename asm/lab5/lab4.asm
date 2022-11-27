; Новиков Е. А., УИС-311
; Вариант 16
; Посчитать среднее арифметическое всех элементов каждой строки массива А

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

; EBX - адресация строк массива А
; ECX - адресация столбцов массива А (с последнего)
; EDI - адресация столбцов массива В
mov ECX,N ;счетчик строк массива A
mov EBX,0 ;смещение первой строки массива A
mov EDI,0 ;смещение первого столбца массива B

row: ;цикл по строкам
mov AL,0; Сумма элементов строки
mov EDX,ECX ;EDX как хранитель значения регистра ECX для внешнего цикла
mov ECX,M ;счетчик столбцов массива A


column: ;цикл по столбцам
add AL,A[EBX][ECX-1]
loop column


cbw ;Преобразование однобайтового AL в двухбайтовый AX (костыль)
mov cl, M ; (костыль)
div cl ;В AL записывается среднее арифметическое
mov B[EDI],AL

mov ECX,EDX ;EDX как хранитель значения регистра ECX для внешнего цикла
add EBX,M ;Переход к следующей строке A(+5)
add EDI,TYPE B
loop row

invoke ExitProcess, 0
end main