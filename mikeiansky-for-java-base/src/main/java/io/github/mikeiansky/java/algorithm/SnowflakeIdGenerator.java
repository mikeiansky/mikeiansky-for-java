package io.github.mikeiansky.java.algorithm;

/**
 *
 * @author mike ian
 * @date 2025/6/12
 * @desc
 **/
public class SnowflakeIdGenerator {
    // 起始时间戳（2020-01-01 00:00:00 UTC）
    private final long epoch = 1577836800000L; // 2020-01-01 00:00:00 UTC in milliseconds

    // 机器 ID 配置（5位数据中心ID + 5位机器ID）
    private final long datacenterIdBits = 5L;
    private final long workerIdBits = 5L;
    private final long maxDatacenterId = ~(-1L << datacenterIdBits); // 31
    private final long maxWorkerId = ~(-1L << workerIdBits); // 31

    // 序列号位数
    private final long sequenceBits = 12L;
    private final long maxSequence = ~(-1L << sequenceBits); // 4095

    // 移位偏移量
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    // 机器 ID 和序列号
    private final long datacenterId;
    private final long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L; // 上次生成 ID 的时间戳

    /**
     * 构造函数
     *
     * @param datacenterId 数据中心 ID (0 ~ 31)
     * @param workerId     机器 ID (0 ~ 31)
     */
    public SnowflakeIdGenerator(long datacenterId, long workerId) {
        // 检查机器 ID 是否合法
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId 必须在 0 ~ " + maxDatacenterId + " 之间");
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("workerId 必须在 0 ~ " + maxWorkerId + " 之间");
        }

        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    /**
     * 获取当前时间戳（毫秒）
     */
    private long currentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 等待下一毫秒（用于序列号溢出时）
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = currentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimestamp();
        }
        return timestamp;
    }

    /**
     * 生成下一个 Snowflake ID
     */
    public synchronized long nextId() {
        long timestamp = currentTimestamp();

        // 如果当前时间小于上次生成 ID 的时间，说明时钟回拨，抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨！当前时间 " + timestamp + " 小于上次时间 " + lastTimestamp);
        }

        // 如果是同一毫秒内生成的 ID，增加序列号
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) { // 序列号溢出，等待下一毫秒
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L; // 新毫秒，序列号从 0 开始
        }

        lastTimestamp = timestamp;

        // 组合 Snowflake ID
        return ((timestamp - epoch) << timestampShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    // 测试
    public static void main(String[] args) {
        // 创建 Snowflake ID 生成器（datacenter_id=1, worker_id=1）
        SnowflakeIdGenerator snowflake = new SnowflakeIdGenerator(1, 1);

        // 生成 10 个 ID
        for (int i = 0; i < 10; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
