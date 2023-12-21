package org.choongang.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Stat {

    // 사용자가 없는 새벽시간대에 통계자료를 사용함

    // @Scheduled(cron = "*/3 * * * * *")
    // 3초마다 진행, 작업이 끝나지 않아도 3초 뒤에 진행하게됨
    // @Scheduled(fixedDelay = 3000) 작업 완료 후 3초 대기
    // @Scheduled(initialDelay = 3000) // 작업 시작 전 3초 대기
    // @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS) 작업 시간 포함 3초 마다
    @Scheduled(initialDelay = 3, timeUnit = TimeUnit.SECONDS)
    // timeUnit -> 매개변수의 시간 단위
    // initialDelay -> 작업이 처음 실행되기 전에 대기해야 하는 초 단위의 초기 지연 시간
    public void orderStatProcess(){
        log.info("주문 통계 진행....");
    }
}
