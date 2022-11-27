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
counter PROC
    push EBP
    mov EBP,ESP ;формирование альтернативного указателя стека ebp=esp
    push ECX ;спасаем используемые в процедуре регистры
    push EBX
    mov AL,0
    mov ECX,[EBP+8]
    mov EBX,[EBP+12]
    column:
        add AL,[EBX]
        inc EBX
    loop column

    cbw ;Преобразование однобайтового AL в двухбайтовый AX (костыль)
    mov cl, M ; (костыль)
    div cl ;В AL записывается среднее арифметическое

    pop EBX
    pop ECX
    pop EBP
    RET 100
counter ENDP

main:
mov ECX,N ;счетчик строк массива A
mov EDI,0 ;смещение первого столбца массива B

; Подготовка к запуску цикла
lea EBX,A
mov EDX,M
row: ;цикл по строкам
    push EBX ;Параметр 1: адрес строки массива А
    push EDX ;Параметр 2: число элементов в строке массива А

    CALL counter

    mov B[EDI],AL
    add EBX,M ;Переход к следующей строке
    add EDI,TYPE B
loop row

invoke ExitProcess, 0
end main