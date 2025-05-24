Features Required

Logging Levels:
Logging should support multiple levels like DEBUG, INFO, WARN, ERROR, and FATAL. 

Helps filter messages based on severity. : i.e you can decide what all logs need to be append to the file

Ensures flexibility to control verbosity.

High Performance:
Must handle 1 million concurrent logs per second, making asynchronous logging and buffering mandatory.

Appenders:
ConsoleAppender: Logs to the console.
FileAppender: Logs to a file.
RollingFileAppender: Supports log rotation based on size or time.

Thread-Safety:
Logs should not get mixed up in a multi-threaded environment.
Asynchronous Logging:

Use a queue to buffer logs.
Worker threads consume from the queue for actual writing.

Formatter/Layouts:
Support customizable message formats like:
[Timestamp] [LogLevel] [Thread] - Message

Configuration:
Provide configuration via a JSON file.
Dynamically update logging behavior during runtime.

Filters:
Allow conditional logging (e.g., only log messages containing specific keywords).

Solution

1. Log interface ->     debug,info,warn,error-Factory Design pattern to create Type of log
2. Appender-> Console, File Appender Factory Design
3. LogFormat-> 


Confiugration
1. LogLevel
2. Filter
3. List<Appendeers>
4. LogFormat

LoggerService
- Configuration
+info
+warn
+debug


Flow:
LoggerService.info(String message)->loggerFactory.(type,message)->format log

proccessLog()->if it passed both Filter. logLevel -> queue



Important:

create seprate thread for logger process runnnign in background
1. new Thread()
2. sepreate thread pool with one thread
3. use own thread pool

LoggerService-> It uses BlockingQueue
LoggerServiceV2->It uses Normal Queue with synchonized

https://docs.google.com/document/d/1TMC-zXVC3176qA_rkpZhnwwagdBTR1ZM_pY_n8WSHH0/edit?tab=t.u8c291286ep4