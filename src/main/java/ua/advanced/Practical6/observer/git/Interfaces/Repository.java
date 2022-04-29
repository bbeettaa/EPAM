package ua.advanced.Practical6.observer.git.Interfaces;

import ua.advanced.Practical6.observer.git.Implementations.Commit;

public interface Repository {
    void addWebHook(WebHook webHook);

    Commit commit(String branch, String author, String[] changes);

    void merge(String sourceBranch, String targetBranch);

}
