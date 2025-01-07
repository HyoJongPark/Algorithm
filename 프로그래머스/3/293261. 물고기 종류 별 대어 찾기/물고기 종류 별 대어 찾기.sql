SELECT I.ID, NI.FISH_NAME, I.LENGTH
    FROM FISH_INFO AS I
    JOIN FISH_NAME_INFO AS NI 
        ON I.FISH_TYPE = NI.FISH_TYPE
    WHERE 
        (I.FISH_TYPE, I.LENGTH) IN (
            SELECT FISH_TYPE, MAX(LENGTH)
                FROM FISH_INFO
                GROUP BY FISH_TYPE
        )
    ORDER BY I.ID;
    