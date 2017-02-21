package com.emadoki.edloader;

public enum LoaderType
{
    CLASSIC(0), GROW(1), SWAP(2), JUMP(3), PACMAN(4);

    public int id;

    LoaderType(int id)
    {
        this.id = id;
    }

    static LoaderType fromId(int id)
    {
        LoaderType type = CLASSIC;

        for (LoaderType t: values())
        {
            if (t.id == id)
            {
                type = t;
                break;
            }
        }

        return type;
    }
}
