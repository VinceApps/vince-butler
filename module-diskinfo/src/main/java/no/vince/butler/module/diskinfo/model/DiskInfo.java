package no.vince.butler.module.diskinfo.model;

/**
 *
 */
@SuppressWarnings("UnusedDeclaration")
public class DiskInfo
{
    private long freeSpace;
    private long totalSpace;
    private long usableSpace;

    public long getFreeSpace()
    {
        return freeSpace;
    }

    public void setFreeSpace(long freeSpace)
    {
        this.freeSpace = freeSpace;
    }

    public long getTotalSpace()
    {
        return totalSpace;
    }

    public void setTotalSpace(long totalSpace)
    {
        this.totalSpace = totalSpace;
    }

    public long getUsableSpace()
    {
        return usableSpace;
    }

    public void setUsableSpace(long usableSpace)
    {
        this.usableSpace = usableSpace;
    }
}
