rem Командный файл для выполнения EXE программ
rem Программист Новиков Е. А.
@echo off
 if exist "%1.obj" del "%1.obj"
 if exist "%1.exe" del "%1.exe"
rem Ассемблирование
C:/masm32/bin/ml.exe /c /coff /Sn /Fl"%1.lst" "%1.asm"
 if errorlevel 1 goto errasm
rem Редактирование связей
C:/masm32/bin/polink.exe /SUBSYSTEM:WINDOWS "%1.obj"
 if errorlevel 1 goto errlink
rem dir "%1.*"
rem Запуск в отладчике
C:\masm32\olly-dbg\OLLYDBG.exe "%1.exe"
 goto TheEnd
:errlink
 echo _
 echo Link error
 goto TheEnd
:errasm
 echo _
 echo Assembly error
 goto TheEnd
:errollydbg
 echo _
 echo OllyDbg error
 goto TheEnd
:TheEnd
rem Выполнение
"%1.exe"