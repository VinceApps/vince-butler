package no.vince.butler.module.diskinfo.service;

import no.vince.butler.module.diskinfo.model.DiskInfo;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 *
 */
@Service
public class DiskInfoService
{
    public DiskInfo getDiskInfo(String rootFolder)
    {
        DiskInfo diskInfo = new DiskInfo();

        final File rootDir = new File(rootFolder);

        diskInfo.setFreeSpace(rootDir.getFreeSpace());
        diskInfo.setTotalSpace(rootDir.getTotalSpace());
        diskInfo.setUsableSpace(rootDir.getUsableSpace());

        return diskInfo;
    }
}
