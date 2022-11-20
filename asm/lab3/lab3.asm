;Лабораторная работа №3
;Выполнил ст. гр. УИС-311 Новиков Евгений
;Вариант 16
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
a db 'Н'
b db 'е'
i db 0100B
j db 0010B
k db 0001B
rez db ?,?
tit db ' Результат '
;=========================================
.code
main:
;1) (A and b) or b => DH +
mov dh, a ;поместить в dh значение a
and dh, b ;умножить значение на b
or dh, b  ;прибавить b

;=========================================
;2) если i бит регистра R = 0, записать 1 в k бит регистра R иначе 0 +
test dh, i ;умножение на маску, оперирует флагом ZF
jnz notNull ;перейти если в результате не 0
or dh, k   ;1 в k бит
jmp nextStep ;перейти к следующему шагу

notNull:
mov al, k
not al ;поместить в al число, обратное k
and dh, al ;запись в dh, умноженного на маску

;=========================================
;3) инверсия j бита с помощью исключающего или
nextStep:
xor dh, j;
;=========================================
;4) поменять тетрады байта вр +
ror dh, 4
;=========================================
;5) dh<32? => dh=dh+32 +
cmp dh, 32 
jae finalStep
add dh, 32
;=========================================
;6) запись в rez результата и вывод
finalStep: 
mov rez, dh;запись кода символа в rez
INVOKE MessageBox, 0, OFFSET rez, OFFSET tit, 0
end main