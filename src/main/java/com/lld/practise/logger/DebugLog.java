package com.lld.practise.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DebugLog implements ILog{
    private final LogLevel logType;
    private final String message;

}
